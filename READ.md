# 29CM 백엔드 과제

## Project Description
- 해당 프로젝트 29CM의 백엔드 전형 과제 프로젝트입니다.
- 별도 첨부된 PDF파일의 요구사항에 맞춰 29CM 의 상품 주문 프로그램을 작성합니다.


## Technologies
- Java 17
- SpringBoot 3.1.1
- JPA
- Lombok
- ArchUnit
- MySQL
- Docker

## Code Convention
- 기본적으로 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) 를 준수합니다.
- 프로젝트 root에 있는 [intellij-java-google-style.xml](./intellij-java-google-style.xml)를 import하여 사용합니다. 

## Package Structure
```
└── source_root
    ├── common
    │ ├── dto
    │ │ ├── request
    │ │ └── response
    │ ├── exception
    │ └── listener
    ├── domain
    │ ├── controller
    │ ├── mapper
    │ ├── persistence
    │ │ ├── dao
    │ │ ├── entity
    │ │ └── repository
    │ └── service
    └── presentation
```
- 기본적인 layered architecture 기반의 package 구조로 구성합니다.
- source_root : 하위에 크게 common, domain, presentation 영역으로 구분합니다.
  - common : domain, presentation에서 공통으로 사용하는 class들의 패키지입니다.
  - domain : domain 관련 class들의 패키지입니다. domain의 볼륨이 작아서 entity 별로 세분화하지 않고 flat하게 구성했습니다.
    - controller
      - web project는 아니지만 presentation과 통신을 담당하는 의미로 class를 정의하였습니다.
    - mapper
      - 각 클래스간 데이터 변환 작업을 수행하는 데 사용합니다. 주로 entity to dto에 사용됩니다.
      - 변환하는 클래스들이 크지 않아서 MapStruct, ModelMapper같은 서드파티를 사용하지 않고 직접 구현했습니다.
    - persistence
      - 데이터를 영구적으로 저장하고 관리하는 데 사용되는 패키지입니다.
      - Repository, DAO로 나뉘어서 관리합니다.
    - service
      - 비즈니스 로직을 담당하는 패키지입니다.
      - 프로젝트 볼륨이 크지 않아 따로 Command, Query등으로 구분하지 않고 하나의 Service만 사용합니다.
  - presentation : 화면에 관련된 class들의 패키지입니다.

## Package Convention
- 해당 프로젝트는 좋은(?) Application 구조와 유지보수성을 위해 package(또는 Layer)간의 무분별한 참조를 지양하고 있습니다.
- layer
  - domain -> presentation 접근 금지
  - domain -> common 접근 금지
  - presentation -> common 접근 금지
- domain package
  - controller -> service -> persistence(DAO -> repository)순으로 참조하며 역방향 참조를 금지합니다.
  - 같은 level의 참조고 금지합니다. ex) service -> service
  - entity class는 외부로 노출을 금지하며 DTO로 변환하여 리턴합니다. 
- ArchUnit으로 일부 강제화 되어있습니다.

## Commit Message Convention
- 커밋 메시지를 기본적으로 아래와 같이 구성합니다.
```
CommitType : subject
(한 줄을 띄워 분리)
contents
```
| Commit Type | Description                        |
|-------------|------------------------------------|
| feat        | 새로운 기능 추가                          | 
| refactor    | 일반적으로 코드를 수정하는 경우 또는 리팩터링 하는 경우    | 
| fix         | 버그 및 오류 수정                         | 
| test        | 테스트 코드를 수정 또는 리팩터링 하는 경우           | 
| style       | 코드(로직)의 변경없이 코드 포맷, 컨밴션 등을 수정하는 경우 |
| chore       | 코드의 수정 없이 설정 등을 변경하는 경우            |
| docs        | 문서를 수정하는 경우                        |
| rename      | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우       |
| remove      | 파일을 삭제하는 작업만 수행한 경우                |
- Contents의 경우 subject로만 설명이 가능한 경우 생략이 가능합니다.
- 별도 이슈 티켓을 관리 안하므로 Commit은 유의미한 단위로 하되 개발자의 판단에 맡깁니다.

## Git Branch Strategy
- N/A

## Installation and Run
- test
  - JUnit으로 구성된 테스트 케이스를 실행합니다.
  - In-Memory DB를 사용하지 않고, Local과 유사한 환경을 위해 testContainers를 사용했ㅅ브니다.
  - 아래 명령어를 입력합니다.
    ```
    ./gradlew test
    ```
- local
  - 로컬 환경에서 Application을 구동합니다.
  - 각 서드 파티(RDBMS, Cache 등)은 Docker에 의존성이 있습니다.
  - gradle을 사용하여 jar로 bulid 후 해당 파일을 실행합니다.
  - 아래 명령어를 입력합니다.
    ```
    1. ./gradlew clean bootJar
    2. java -jar ./build/libs/*.jar --spring.profiles.active=local
    ```

## Application Profile
- test
    - 테스트 코드가 실행되는 profile입니다.
    - 테스트 코드에서 의존 구성요소를(e.g. 외부 통신, 다른 서비스 등) 사용할 수 없을 때는 **테스트 더블**을 사용하여 테스트합니다.
- local
    - 로컬 환경에서 실행되는 profile입니다.
    - 의존 구성요소는 docker-compose를 통해 실행합니다.
    - docker-compose로 해결 할 수 없는 경우 Profile별로 별도 로직을 수행할 수 있도록 합니다.
- 과제 프로젝트이므로 test, local Profile 외에는 지원하지 않습니다. 

## CI/CD Principle
- N/A