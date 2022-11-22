문제 정의

처방받은 약을 구분하기 어려운 상황에서 해당 약의 정보를 제공해 알약의 오용과 남용을 줄이기 위해 
알약의 외견을 통해 알약의 정보를 검색해, 사용자에게 제공하는 것이 최종 목표이다.

프로젝트 기능
![image](https://user-images.githubusercontent.com/83694715/203320666-2f15f5ec-0cc3-42a9-8e73-1f6fafef14e8.png)

시스템 모식도

![image](https://user-images.githubusercontent.com/83694715/203320302-3d6bead7-321a-4ced-8144-e1ae9dcc0182.png)

해당 프로젝트의 구성 요소를 크게 3가지로 나누면 모델의 학습, 서버 구축, 앱 제작으로 나눌 수 있다.

사전 준비로 학습시킬 이미지를 수집하고, 수집한 데이터를 증강 및 전처리 작업을 거쳐 학습시킬 이미지를 늘린다.

수집한 데이터를 코랩 환경에서 딥러닝 모델과 Yolo 모델을 학습시킨다.

학습된 모델을 서버에 구현하게끔 서버환경을 구축한다.

알약을 검색할 수 있게끔 기능을 구현한 앱을 개발하고 서버와 연동한다.



가)사전 준비

1)Imgaug

학습시키는 알약 외형의 데이터를 증강시켜 학습시의 Overfitting을 방지한다. 이미지 증강을 위한 라이브러리, Imagug 라이브러리를 활용하여 노이즈 추가, 흔들림, 이미지의 회전 효과를 가해 유사하지만 변형을 가한 데이터를 수집한다. 

2)Roboflow

무료 데이터셋 제작을 지원하는 Roboflow 웹사이트를 통해 바운딩 박스처리나, 데이터 증강을 가해 변형을 가한 데이터를 수집한다.


나)모델 학습

1)Resnet34 전이 학습

알약 분류를 위한 모델은 Resnet34을 활용해 전이학습으로 학습시켜, 학습 난이도를 낮추고 높은 정확도를 얻고자 하였다.

2)Yolov5 커스텀 학습

알약 분류의 정확도를 높이기 위해 Yolov5를 활용해 약을 탐지해 배경을 제거해 약의 외형만을 분류하고자하였다.


다)앱 제작

1)안드로이드 스튜디오를 사용하여, 알약을 검색하고 추론결과를 사용자에게 제공하는 기능을 앱을 제작하였다.


라)서버 환경 구축

1)Groomidle

설치 없이 웹 브라우저에서 서버를 개발할 수 있게 제한적이지만 무료 GPU를 제공하는 클라우드 서비스로, Groomidle에서 해당 프로젝트의 서버로 활용하고자 하였다

2)Colab

구글에서 제공하는 브라우저 기반의 주피터 노트북 사용 환경으로 GPU를 사용할 수 있다. Colab을 사용하여 Ngrok을 활용해 해당 프로젝트의 서버로 활용하고자 하였다.


마)연동

1)Retrofit2

안드로이드와 서버간의 REST API 통신을 도와주는 라이브러리로, 높은 성능과 뛰어난 가독성을 통해 서버와 앱의 통신을 원활히 하고자 하였다.