# つみメモ書籍管理アプリ

Spring Bootで作成した書籍管理Webアプリです。  
Google Books APIを使用して書籍を検索し、登録・一覧表示が可能です。

## 主な機能
- 書籍の検索（Google Books API連携）
- 書籍情報の登録・編集・削除
- ユーザー認証（Spring Security）
- ISBNバーコード読み取り（スマホ対応）
- レスポンシブデザイン（Tailwind CSS）

## 技術スタック
- Java / Spring Boot / Spring Security
- Thymeleaf / Tailwind CSS
- H2 Database（開発時）

## セットアップ方法
```bash
git clone https://github.com/keisixers/tsumimemo-bookapp.git
cd tsumimemo-bookapp
./mvnw spring-boot:run
