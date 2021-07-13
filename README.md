## 프로젝트명

```
Love Bullet
```

## 팀원

- 김영경
- 이주은
- 최정재

## 프로젝트 설명

```
하트 총알을 쏘아 친구들을 맞추는 게임
한명이라도 하트를 받지 못하면 게임을 종료된다.
카카오톡 로그인 정보를 이용하여 로그인 할 수 있고, 
로그인 정보를 받아 mongoDB를 이용한 데이터 베이스에 저장한다.
레트로핏을 이용하여 서버와 http 통신을 하여 앱에서 서버로부터 데이터를 받을 수 있게 하였다.
게임 최고점수도 같이 저장하며 모든 사용자의 랭킹을 확인할 수 있다.
또한 모든 사용자들은 채팅방에 원하는 닉네임으로 들어가 서로 소통할 수 있다.
```

## 상세설명

### 게임 실행화면

<img src="https://user-images.githubusercontent.com/80809782/125423283-e8be0b0b-7fa7-4e40-8a72-652750710466.png" width="200" height="400">
<img src="https://user-images.githubusercontent.com/80809782/125423491-dfe5f285-0aee-4808-baa9-d768e962ac23.png" width="400" height="200">
<img src="https://user-images.githubusercontent.com/80809782/125423653-f797191a-4a4f-4682-82ea-47cec53bd9cd.png" width="400" height="200">
<img src="https://user-images.githubusercontent.com/80809782/125423707-8575a95c-4a96-4a1b-bdbd-2daf6812a537.png" width="400" height="200">

```
앱을 실행시키면 카카오톡 로그인 화면이 뜨고, 로그인을 하면 게임 플레이, 랭킹, 마이페이지, 채팅 버튼이 뜬다.
먼저 플레이 버튼을 누르면 게임이 실행된다. 
게임 액티비티에서 surface 뷰를 사용해 게임화면을 띄우게 하였고 게임 뷰에서 게임이 종료되기 전까지 
update, draw, sleep 함수를 반복적으로 불러와 화면이 움직이는 것처럼 보이게 하였다.
```

### 랭킹화면

<img src="https://user-images.githubusercontent.com/80809782/125423796-d00c930a-bd94-4676-9b1e-4188c0cfe2cc.png" width="200" height="400">

```
랭킹확인 버튼을 누르면 몽고디비에 JSON 형식으로 저장된 점수 데이터를 
스트링 형태로 변환해 받아오고, 
리스트에 추가한다음 점수가 높은 순으로 sorting하여
리사클러뷰에 랭킹을 띄우도록 하였다.
```

### 마이페이지

<img src="https://user-images.githubusercontent.com/80809782/125423831-196d8508-8c12-4742-bc21-90eb78bb3fe4.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/125423851-f69e16de-49c6-47b7-93f3-38349c5c23dd.png" width="200" height="400">

```
마이페이지 버튼을 누르면
디비에서 현재 로그인된 사용자의 정보를 보내 그에 맞는 사용자의 닉네임과 최고점수를 받아 화면에 띄웠다.
```

### 채탱 화면

<img src="https://user-images.githubusercontent.com/80809782/125423934-3c80bbc8-a7b8-4653-a1f6-b68b8805c355.png" width="400" height="200">
<img src="https://user-images.githubusercontent.com/80809782/125423982-4214df64-05cb-4356-a214-3bd84646976f.png" width="200" height="400">

```
서버에서 웹소켓을 사용하여 실시간으로 채팅을 여러명이서 할 수 있게 하였다. 
png 형태의 이미지 파일도 비트맵 형식으로 변환해채팅으로 서로 주고 받을 수 있게 하였다.
```
