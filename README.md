# SingleFunctionApps
いくつかの単機能のアプリが並びます．

## Auth
Firebase Authenticationでトークンを取得するアプリです．

[ログインが必要になったらFirebase Authenticationでトークンを取るフラグメント出す for Android](https://zenn.dev/log_suzaki/articles/25086cfefbadd8)

## GPSApp1
FusedLocationを使い、携帯が取得した最後の位置情報を表示するアプリです。
ボタンを押すともう一度表示します。

## MinioClient
[minio](https://min.io)にファイルをPOSTするアプリです．

minioとはAmazon S3クラウドストレージサービスとAPI互換を持つオブジェクトストレージです．

Amazon S3のオンプレミス版です．

minioを使用してAWSS3にもファイルを送信できると思います．

## Queue
センシングをするアプリです．

取得したセンサの値をQueueに格納し，一定間隔ごとにQueueの中身を外部ストレージに保存します．

## TextToSpeechApp
文章を読み上げるアプリです。