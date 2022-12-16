<img width=830 src="https://user-images.githubusercontent.com/82090641/207493959-329e70a8-3ec0-46e5-9a21-d923a02e7320.png">

## 성심당과 함께하는 사내 인트라넷 애플리케이션 서버입니다!

#### [Application Programming Interface Spec](https://documenter.getpostman.com/view/23766185/2s8YswQrDX)

### Contants

- [개발 프로세스](https://github.com/Team-ComIT/SimTong-Backend/edit/main/README.md#개발-프로세스)
- [Issue 규칙](https://github.com/Team-ComIT/SimTong-Backend/edit/main/README.md#Issue-규칙)
- [Commit 규칙](https://github.com/Team-ComIT/SimTong-Backend/edit/main/README.md#Commit-규칙)
- [Branch 규칙](https://github.com/Team-ComIT/SimTong-Backend/edit/main/README.md#Branch-규칙)
- [Version 규칙](https://github.com/Team-ComIT/SimTong-Backend/edit/main/README.md#Version-규칙)

## 개발 프로세스

1. **Issue 생성**
2. **Issue 기반 Branch 생성**
3. **Issue 관련 작업 완료**
4. **PullRequest 생성 및 변경사항 작성**
5. **PullRequest Merge 조건**
   - **1인 이상의 Approve 필요**
   - **Review Resolve 및 코드 개선**
   - **CI로 인한 Build Success**
   - **Test Case 작성 (예외있음)**
   - **Code Coverage Passed (예외있음)**
6. **PullRequest Merge 실행**

### Issue 규칙

> 기능 추가, 리팩토링, 버그 수정 등 모든 작업들은 이슈 생성을 권장합니다.
>
> 이슈 메시지는 최대한 한국어를 사용합니다. 

- 작업 내용 설명
- 추가 사항 기재
- Assignees 등록
- Labels 추가
- 작업에 따른 Projects, Milestone 선택

### Labels

|      Label      |     Description     | CodeColor |
| :-------------: | :-----------------: | :-------: |
|       bug       |     버그가 발생한 경우      |  #d73a4a  |
|      defer      |      보류 중일 경우       |  #cfd3d7  |
|      docs       |  문서가 추가되거나 변경되는 경우  |  #D4C5F9  |
|      feat       |   새로운 기능을 추가 할 경우   |  #0075ca  |
| need discussion |     논의가 필요한 경우      |  #8A4B08  |
|   performance   |     성능을 개선 할 경우     |  #d876e3  |
|    refactor     |    코드 리팩토링 할 경우     |  #008672  |
|     setting     | 프로젝트의 기본적인 설정을 할 경우 |  #F9D0C4  |
|      test       |    테스트를 추가 할 경우     |  #FBCA04  |

## Commit 규칙

#### Commit 구성
```
CommitType: (#IssueNumber) Subject
```

### CommitTypes

|CommitType|           Description            |
|:--------:|:--------------------------------:|
| add |           파일을 추가하는 작업            |
| feat |       기존의 파일에 기능을 추가하는 작업        |
| ci |        ci 구성 파일 및 스크립트 변경        |
| chore |       패키지 생성 및 변경, 간단한 작업        |
| refactor |   기존의 기능을 벗어나지 않고 코드를 변경하는 작업    |
| docs |            문서 작성 및 변경            |
| style | 클래스 명, 메소드 명 등 코드에 영향을 주지않는 변경사항 |
| build |    시스템 또는 외부 종속성에 영향을 끼치는 작업     |
| fix |              버그 수정               |
| test |         테스트 케이스 작성 및 수정          |
| revert |             작업 되돌리기              |
| perf |              성능 개선               |
| hotfix |             매우 급한 작업             |

### IssueNumber

> 해당 작업의 이슈 번호를 기재해야합니다.
>
> 이슈를 생성하지 않은 hotfix의 경우 이슈 를 기재하지 않아도 됩니다.

### Subject

> 작업 설명을 개조식으로 작성합니다.
> 
> 가능하면 최대한 한국어로 작성하는 것을 권장합니다.

```
~ 추가했음, Add ~ → ~ 추가
```

## Branch 규칙

> Github Flow 방식을 사용합니다.

```
{BranchType}/{IssueNumber}-{WorkSummary}
```

### BranchTypes

|BranchType|Description|
|:--------:|:---------:|
| feature | 기능을 추가할 때 |
| refactor | 대체적인 리팩토링을 실행할 때 |
| hotfix | 급한 작업을 실행할 때 |
| chore | 패키지 생성 및 변경 작업 |
| docs | 문서 작성 작업을 실행할 때 |
| performance | 성능을 개선할 때 |
| build | 시스템 또는 외부 종속성에 영향을 끼치는 작업 |
| fix | 버그 수정 작업 |
| test | 테스트 케이스 작성 및 수정 작업 |

### IssueNumber

> 해당 브랜치의 이슈 번호를 작성합니다.

### WorkSummary

> 브랜치 설명을 간단하게 요약합니다.
>
> space 대신 하이픈(-)을 사용하고 소문자 영어로 작성합니다.

## Version 규칙

<img width=520 src="https://user-images.githubusercontent.com/67373938/119933978-0ac15300-bfc0-11eb-99cd-0198b1ee6f2d.png">

> 모든 버전은 01.00.00에서 시작합니다.

```
"01.01.09" 생략시 "1.1.9"

"01.01.10" 생략시 "1.1.10"
```

- 기존 버전과 호환되지 않도록 API가 변경되면 **Major Version**를 올립니다.
- 기존 버전과 호환되면서 새로운 기능을 추가할 때는 **Minor Version**를 올립니다.
- 자잘한 버그나 내부적 코드 보완 등의 변화가 발생했을때 **Patches**를 올립니다.
