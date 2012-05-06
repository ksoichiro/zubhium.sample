# zubhium.sample

Zubhiumを使用したAndroidアプリケーションのサンプルです。

[Zubhium](https://www.zubhium.com/,"Zubhium")は、Androidアプリケーションをベータ版として配布するのに役立つサービスです。
配布、クラッシュレポート収集、ユーザからのフィードバック取得などを簡単に行えます。

サービスは英語で提供されており、フィードバックのダイアログも英語で表記されています。
しかし、各国用のアプリケーションとして提供するには
ダイアログ内の文言をローカライズしたいところです。
このサンプルは、そのフィードバックのダイアログをカスタマイズして日本語化したものです。

# 使い方

1.  このプロジェクトをダウンロードします。

2.  Zubhium SDKをダウンロードして zubhiumsdk.jar をプロジェクトに組み込みます。
    libs/zubhiumsdk.jarに配置します。

3.  libs/zubhiumsdk.jarをビルドパスに設定します。

4.  Zubhium利用のために各アプリケーション専用のシークレットキーを設定します。
    シークレットキーの記述場所はFeedbackUtils#ZUBHIUM_APP_SECRET_KEYです。
    Zubhiumにアプリケーションを登録した後、発行されたシークレットキーをここに記載してください。

# ライセンス

The project is licensed under the MIT license.

Copyright (c) 2012 Soichiro Kashima

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
