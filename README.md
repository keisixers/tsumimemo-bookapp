# つみメモ書籍管理アプリ

Spring Boot で作成した書籍管理 Web アプリです。  
Google Books API を使用して書籍を検索し、登録・一覧表示が可能です。

## 主な機能

- 書籍の検索（Google Books API 連携）
- 書籍情報の登録・編集・削除
- ユーザー認証（Spring Security）
- ISBN バーコード読み取り（スマホ対応）
- レスポンシブデザイン（Tailwind CSS）

## 技術スタック

- Java 17
- Spring Boot / Spring Security
- Thymeleaf / Tailwind CSS
- H2 Database（開発時のみ使用）

## セットアップ方法

1. このリポジトリをクローン

```bash
git clone https://github.com/keisixers/tsumimemo-bookapp.git
cd tsumimemo-bookapp
```
