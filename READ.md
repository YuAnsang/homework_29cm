# 29CM 백엔드 과제

## Project Description
- 해당 프로젝트 29CM의 백엔드 전형 과제 프로젝트입니다.
- 별도 첨부된 PDF파일의 요구사항에 맞춰 29CM 의 상품 주문 프로그램을 작성합니다.


## Technologies
- TBD

## Code Convention
- 기본적으로 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) 를 준수합니다.
- 프로젝트 root에 있는 [intellij-java-google-style.xml](./intellij-java-google-style.xml)를 import하여 사용합니다. 

## Package Structure
- TBD

## Package Convention
- TBD

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
- 별도 이슈 티켓을 관리 안하므로 Commit은 유의미한 단위로 개발자의 판단에 맡깁니다.

## Installation and Run
- TBD

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
N/A