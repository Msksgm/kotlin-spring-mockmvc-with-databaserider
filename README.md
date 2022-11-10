# kotlin-spring-mockmvc-with-databaserider

本記事は、zenn に投稿した記事[Kotlin（Spring Boot）+ MockMVC + DatabaseRider で簡単 API 結合テスト](https://zenn.dev/msksgm/articles/20221111kotlin-spring-mockmvc-with-databaserider)のサンプルコードです。

# API 設計

https://msksgm.github.io/kotlin-spring-mockmvc-with-databaserider/swagger/

# 使い方

DB の起動方法。Web API の実行または、API テストに必要です。

```bash
docker compose up
```

API サーバーの起動。API テスト時に起動している必要はありません。

```bash
./gradlew bootRun
```

API テスト。

```bash
./gradlew test
```
