# Deep-Military-Camera

## 1. 개요
* 오픈소스 라이브러리(Tensorflow Framework)를 활용하여 거동수상자 탐지 및 상황전파를 신속하게 할 수 있고 기기확장성이 뛰어난 무인감시 시스템
   ### 가. 컴퓨터 구성 / 필수조건 안내
      - 권장 : 안드로이드 API 레벨 28 (Android 9.0, Pie)
      - 최소 : 안드로이드 API 레벨 21 이상(Android 5.0, Lollipop)
   ### 나. 설치 안내
      - git clone https://github.com/MaliciousJ/Deep-Military-Camera.git
      - 바. 알려진 버그 항목 참조하여 안드로이드 스튜디오에서 빌드 후 실행
   ### 다. 사용법
    |![ex_screenshot](https://github.com/MaliciousJ/Deep-Military-Camera/blob/master/sample_images/%EC%B2%AB%EC%8B%A4%ED%96%89.png)|      ![ex_screenshot](https://github.com/MaliciousJ/Deep-Military-Camera/blob/master/sample_images/%EC%9C%A0%EC%A0%80%EB%AA%A9%EB%A1%9D.png)|![ex_screenshot](https://github.com/MaliciousJ/Deep-Military-Camera/blob/master/sample_images/%EC%B1%84%ED%8C%85%EB%AA%A9%EB%A1%9D.png)|
    |:----------:|:-------------:|:------:|
    |1. 첫 실행/권한 요청|2. 유저 목록|3. 채팅방 목록|
    |![ex_screenshot](https://github.com/MaliciousJ/Deep-Military-Camera/blob/master/sample_images/%EB%B3%B5%EC%88%98%EC%9D%B8%EC%8B%9D.png)|![ex_screenshot](https://github.com/MaliciousJ/Deep-Military-Camera/blob/master/sample_images/%EA%B1%B0%EB%8F%99%EC%88%98%EC%82%AC%EC%9E%90%EC%9D%B8%EC%8B%9D.png)|![ex_screenshot](https://github.com/MaliciousJ/Deep-Military-Camera/blob/master/sample_images/%EC%84%B8%EB%B6%80%EC%B1%84%ED%8C%85.png)|
    |4. 거수자 인식화면|5. 거수자 보고 및 확인|6: 채팅방| 
   ### 라. 파일 정보 및 목록
      -  ├─activity : DMC의 각종 화면을 나타내는 액티비티 디렉토리
         │      CameraActivity.java
         │      ChatActivity.java 
         │      DetectorActivity.java
         │      LoginActivity.java          
         │      MainActivity.java         
         │      SelectUserActivity.java 
         │      SplashActivity.java
         │      UserPWActivity.java
         ├─detector : Tensorflow의 학습모델 및 트래킹을 담당하는 디렉토리
         │      MultiBoxTracker.java
         │      ObjectTracker.java       
         │      TensorFlowImageClassifier.java        
         │      TensorFlowMultiBoxDetector.java        
         │      TensorFlowObjectDetectionAPIModel.java
         │      TensorFlowYoloDetector.java
         ├─env : : Firebase 및 라이브러리가 들어있는 디렉토리
         │      BorderedText.java     
         │      FirestoreAdapter.java      
         │      ImageUtils.java      
         │      Logger.java      
         │      MyAppGlideModule.java      
         │      MyFirebaseMessagingService.java      
         │      Size.java
         │      SplitTimer.java
         ├─fragment : DMC의 액티비티에서 각종 정보를 표시해주는 프래그먼트 디렉토리
         │      CameraConnectionFragment.java         
         │      ChatFragment.java         
         │      ChatRoomFragment.java         
         │      LegacyCameraConnectionFragment.java         
         │      UserFragment.java         
         │      UserListFragment.java         
         │      UserListInRoomFragment.java         
         ├─model : Firebase 및 사용자의 정보를 정의해주는 모델 디렉토리
         │      ChatModel.java      
         │      ChatRoomModel.java 
         │      Classifier.java  
         │      GlobalBus.java       
         │      Message.java
         │      NotificationModel.java         
         │      ResultsView.java         
         │      UserModel.java         
         └─view : Tensorflow 및 DMC의 화면을 그려주는 뷰 디렉토리       
                AutoFitTextureView.java     
                HackyViewPager.java           
                OverlayView.java           
                RecognitionScoreView.java  
                ViewPagerActivity.java 
    
   ### 마. 배포자 및 개발자의 연락처 정보
      - 최준혁(sposent7@naver.com / 042-553-6625)
      - 김형민(hmkim7028@gmail.com / 042-553-5752)  
      
   ### 바. 알려진 버그
      - Android Studio에서 프로젝트 빌드시 'File google-services.json is missing.
        The Google Services Plugin cannot function without it.' 메세지와 함께 실패. 
        
   ### 사. 문제 발생에 대한 해결책
      - 파이어베이스 이용을 위한 환경설정 파일인 'google-services.json' 파일이 민감정보를
        포함하고 있기때문에 GitHub 업로드시 누락되어 일어나는 현상으로 [파이어베이스 콘솔] -
        [프로젝트 설정] - [내 앱] 탭의 최신 구성 파일 다운로드를 통하여 프로젝트 최상위에 추가시켜준다.
        (참고 : https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221406814289&proxyReferer=https%3A%2F%2Fwww.google.com%2F)
     
   ### 자. 업데이트 정보
      - Oct 23, 2019
         - otto eventbus를 이용해 거동수상자 감지 시 이벤트 전달 기능 추가
         - 레이아웃 및 코드 갈무리
      - Oct 22, 2019
         - GCM(Google Cloud Message) 메시지 Receiver 및 Broadcast 기능 구현
         - 거동수상자 감지 기능 추가
         - 프래그먼트 및 뷰 구성
         
      - Oct 21, 2019
         - Tensorflow Framework를 이용한 객체 탐지 기능 구현
         - Firebase 기반 메시지 송수신 기능 구현
         - 기초 레이아웃 구성
      
   ### 아. 크레딧
      - Google Developers.
      - junyoung-jamong(Kang Jun Young, msmapark2@gmail.com)
      - webnautes
      - Google Archive
      - mystoryg(서준수)
      
   
   ### 차. 저작권 및 사용권 정보
      - Tensorflow  
                          Apache 2.0 Open Source Library

             Copyright 2019 The TensorFlow Authors.  All rights reserved.

                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
                        
                        
      - Otto

        Copyright 2013 Square, Inc.

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
     
## 2. 기사 모음

* [싼 게 비지떡, IP 카메라](https://www.pentasecurity.co.kr/column/%EC%8B%BC-%EA%B2%8C-%EB%B9%84%EC%A7%80%EB%96%A1-ip-%EC%B9%B4%EB%A9%94%EB%9D%BC/)


* [군 경계 시스템 사업에 ‘지능형 CCTV 인증제도’ 도입](https://www.boannews.com/media/view.asp?idx=66152)

* [왜 거의 다 ‘인재(人災)’라고만 하죠?](http://www.econovill.com/news/articleView.html?idxno=308623)

* [[단독] 스마트폰으로 작전지시하는 軍…10년 헛수고 무전기사업 재시동 - 매일경제](https://www.mk.co.kr/news/politics/view/2019/07/520019/)

* [전차 등에 장착 軍후방카메라, 밤이면 먹통](http://www.donga.com/news/article/all/20191007/97757086/1)

* [스마트폰으로 작전지시하는 軍…10년 헛수고 무전기사업 재시동](https://www.mk.co.kr/news/politics/view/2019/07/520019/)

## 3. 참고자료

* [Detect Facial Features in Photos](https://developers.google.com/vision/android/detect-faces-tutorial)

* [Android에서 내가 학습한 YOLO 모델을 이용해 Object Detection 구현하기](https://junyoung-jamong.github.io/machine/learning/2019/01/25/Android%EC%97%90%EC%84%9C-%EB%82%B4-YOLO%EB%AA%A8%EB%8D%B8-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0.html)

* [안드로이드 카메라 예제 ( 프리뷰 및 사진찍기 )](https://webnautes.tistory.com/822)

* [googlearchive/android-Camera2Basic ](https://github.com/googlearchive/android-Camera2Basic/blob/master/Application/src/main/res/layout/fragment_camera2_basic.xml)

* [안드로이드 카메라 예제 스터디](https://brunch.co.kr/@mystoryg/54)
