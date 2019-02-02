# 될 때까지 안드로이드

<img src="https://raw.githubusercontent.com/junsuk5/android-first-book/master/resources/book_cover.png" width="240">

* [Yes24](http://www.yes24.com/24/goods/59298937?scode=032&OzSrank=1)
* [알라딘](http://www.aladin.co.kr/shop/wproduct.aspx?ItemId=137817656)
* [교보문고](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791186710289&orderClick=LAG&Kc=)
* [반디앤루니스](http://www.bandinlunis.com/front/product/detailProduct.do?prodId=4158780)
* [인터파크](http://book.interpark.com/product/BookDisplay.do?_method=detail&sc.shopNo=0000400000&sc.prdNo=281409749&sc.saNo=003002001&bid1=search&bid2=product&bid3=title&bid4=001)

## 예제 코드 실행 방법
하나의 프로젝트에 예제가 각각의 모듈로 되어있습니다. 안드로이드 스튜디오에서 프로젝트를 Open하고 실행하고 싶은 모듈을 선택하여 실행합니다.

## 예제 라이브 코딩 및 A/S
* [유튜브](https://www.youtube.com/playlist?list=PLxTmPHxRH3VWTd-8KB67Itegihkl4SVKe)
* [페이스북](https://www.facebook.com/untilandroid)

## 강의 교안 슬라이드
구글 드라이브로 작성한 강의 교안입니다. PDF, PPT 등으로 다운 받아 사용하실 수 있습니다.
* http://bit.ly/2qvySfw


## 33장 미세먼지 앱 관련 중요 변경사항

책 출간 이후에 서비스 제공처가 변경되어 API요청주소는 http://apis.skplanetx.com/ 에서 http://api.weatherplanet.co.kr/ 로 변경되었습니다.
앱키는 6b200e091d1a4d7e83fb9b4732809b33를 사용합니다. (일일 500회 요청 제한)


## 오탈자 및 오류, 변경사항

* 오류 페이지 관련 공지
  - 책 초기 인쇄분에 인쇄 오류가 있습니다. 24~29쪽이고 출판사에서 해당 부분 PDF를 제공하며 문제가 있는 책은 교환해 드리니 링크를 참고해 주세요.
  - http://www.rubypaper.co.kr/notice/70

* 76쪽 스크린샷 수정
  - EditText가 가로로 꽉 차는 스크린샷이어야 함.
  
  
* 107쪽 실행결과 이미지 밑에 "숫자만 입력하는 소프트 키보드"라고 주제 외의 말이 적혀있습니다.
  - 삭제

* 117쪽 5번째 줄
  - layout_weigth -> layout_weight 

* 120쪽 닥 -> 바닥
  - 텍스트뷰의 영역에서 '바'닥에 중력이 작용하는 것이지요.
  
* 143쪽 맨밑에서 3번째줄에
  - appcombat -> appcompat
  
* 183쪽 소스 두 번째 액티비티의 paretActivityName 수정
  - parentActivityName=".ChildActivity" -> parentActivityName=".parentActivity"


* 189쪽 하단 단락에 Select에 S 누락
  - elect Methods to ... -> Select Methods to
 
 
* 233쪽 하단 소스에 오타
  - mWeatherImagaMap -> mWeatherImageMap 으로 수정

* 256쪽 소스파일제외 맨밑에서 4번째줄 ImageView. SetImageDrawable 점과 Set 사이에 공백 하나가 있음
 
* 266쪽 12.3.3. 제목
  - 깅제 종료 및 실행 => 강제 종료 및 실행
  

* 282쪽 액티비티에서 프래그먼트에 접근하는 예 코드에서
  - getFragmentManager() => getSupportFragmentManager() 로 수정
  
* 293쪽 // 종료처리 코드 누락
  - getActivity().finish();
  
* 310쪽 상단 스크린샷 패키지명이 다른게 들어가 있음

* 345쪽 맨아래줄에서 바로윗줄 
  - Specific Resion Only -> Specific Region Only

* 355쪽 중간에 오타
  - BroadcastReciever => BroadcastReceiver

[독자 의견]
* 362쪽 중간
  - "MY_ACTION이란 이름의 상수를 정의하고 이 액션으로 들어오는 방송을 걸러내도록 조건을 추가하였습니다." 이 부분의 내용이 바로 위에있는 소스랑 흐름이 조금 안 맞는 것 같습니다! 위의 소스에서는 전원 연결 브로드캐스트 조건만 있습니다! 다음파트인 17.2.2. 나만의 방송 수신하기가 있기때문에 기존 내용을 "MY_ACTION이란 이름의 상수를 정의하였습니다. 이제 액션을 발송하도록 코드를 작성해야겠지요." 로 바꾸면 내용이 자연스러워질 것 같습니다

* 358쪽 MyReceiver.java 소스에서 MY_ACTION 상수 불필요

[독자 의견]
* 359쪽
  - 359페이지에 오레오에서 동작제한 내용이 있습니다. 보면 "오레오 버전부터는 앱이 동작하는 중에만 방송을 수신할 수 있도록 변경되었기 때문입니다."
이 부분 말대로라면 메니페스트에 인텐트 필터를 추가하는 방식인 글로벌 브로드캐스트 리시버라도 앱이 실행된 상태에서는 작동되야하는게 아닌가요? 앱이 켜진상태에서 USB를 뺐다 꼽아도 토스트메세지가 뜨질 않아서 질문드립니다!
오레오부터는 그냥 실행상태라도 "모든 암시적 인텐트"를 메니페스트에서 사용할 수 없다고 이해하는게 맞을까요?
=> 오레오부터는 모든 암시적 인텐트를 메니페스트에서 사용할 수 없고 예외는 있습니다.

* 369쪽 상단 소스 제목
  - [ContentResolver를 통해 데이터를 얻는 예]
  
* 379쪽 하단 path -> uri 로 수정
  - imageView.setImageURI(Uri.parse(uri));

* 399쪽 내용중 첫째줄에 new DownloadFilesTask()~~ 이 부분
  - DownloadFilesTask() -> DownloadTask()

* 414쪽 밑에서 두 번째 줄 delete 실패시 반환 값
  - -1을 반환 => 0을 반환
  
* 427쪽 아래 예제 
  - mAdapter = new MemoAdapter(this, cursor) -> mAdapter = new MemoAdapter(this, getMemoCursor())

* 465쪽 22.2.2절 HttpURLConnect 띄어쓰기 오타

* 472쪽 3번째줄 MainActivity. java 띄어쓰기 오타

* 479쪽 소스 코드내의 "fullname": "Bob" 라인 모두 삭제
  
* 512쪽 6번째 줄
  - onPause() => onStop()
  
* 537쪽 상단 문단
  - getBraodCast() => getBroadcast() 로 수정
  
* 542쪽 중간 코드 경로
  - android_maps.xml => activity_maps.xml 로 수정
 
* 554쪽 Google Play services와 연결하기 부분 삭제 
  - 과거에는 위치정보를 얻기 위해 GoogleApiClient가 필요했었는데 최근에는 FusedLocationProvider를 사용하게 되면서 필요없게 되었기 때문에 해당 절은 필요 없는 부분입니다. 관련된 코드 또한 작성하지 않아도 됩니다.
  
* 576쪽 밑에서 두 번째
  - Open ediotr -> Open editor
  
* 614쪽 상단 소스 두 번째 줄
  - LinearLayoutManager.VERTICAL -> GridLayoutManager.VERTICAL
  
* 616쪽 본문 두번째줄에 
  - 태그를 정의하였 습니다. (띄어쓰기 오타) -> 태그를 정의하였습니다.

* 629쪽 첫번째줄 
  - "별도로 지정합 니다" (띄어쓰기) -> 별도로 지정합니다
  
* 641쪽 밑에서 두 번째 줄
  - 위과 같은 인턴트 필터를 -> 위와 같은 인텐트 필터를
  
* 647쪽 snap 설명
  - "말았더라고" -> "말았더라도"
  
* 676쪽 첫번째줄
  - "getUser() 메서드를 호출하면~" -> "getUser("test123") 메서드를 호출하면~"
  
* 680쪽 첫번째줄
  - "매니페이스 파일에서~" -> "매니페스트 파일에서~"

* 702쪽 첫째줄
  - "기존의 구글 애널리틱스 서비스를 사용했다면~" -> "기존의 파이어베이스 애널리틱스 서비스를 사용했다면~"

* 738쪽 3번째 줄 에제 -> 예제


* 774쪽 하단 표 '미세먼지 API 요청 기본 정보'의 Resource URI 수정 
  - http://api.weatherplanet.co.kr/~ 를 http://apis.skplanetx.com/~ 로 수정
  
  
* 776쪽 스크린샷에 API주소 수정
  - api.weatherplanet.co.kr 을 apis.skplanetx.com 으로 수정
  
  
* 794쪽 하단의 소스 경로 수정
  - finedust/FineDustFragment.java 를 MainActivity.java 로 수정
  
  
* 806쪽 상단 소스의 조건식 수정
  - mTabLayout.getSelectedTabPosition() - 1 < 0 을 mTabLayout.getSelectedTabPosition() == 0 으로 수정

