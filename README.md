# SingleFunctionApps
いくつかの単機能のアプリが並びます．

## Auth
Firebase Authenticationでトークンを取得するアプリです．

[ログインが必要になったらFirebase Authenticationでトークンを取るフラグメント出す for Android](https://zenn.dev/log_suzaki/articles/25086cfefbadd8)

## MinioClient
minioにファイルをPOSTするアプリです．
minioとはAWSのS3をオンプレミスに運用するみたいにできるオブジェクトストレージです．
多分minioを使用してAWSS3にもファイルを送信できると思います．

## Queue
センシングをするアプリです．
取得したセンサの値をQueueに格納し，一定間隔ごとにQueueの中身を外部ストレージに保存します．
