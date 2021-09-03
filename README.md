# EasyResourcePack

## コマンド

| コマンド名 | 説明 | パーミッション |
|----------|------|-------------|
| `/pack` | リソースパックを再度適用します | general |
| `/pack reload` | コンフィグをリロードします | admin |
| `/pack get` | 現在の設定を確認します | admin |
| `/pack set <URL>` | リソースパックのURLを変更します | admin |
| `/pack update` | リソースパックのSHA-1を再生成します | admin |
| `/pack force <ID>` | 指定したプレイヤーのリソースパックを再度適用します | admin |
| `/pack force <ID> <URL>` | 指定したプレイヤーのリソースパックを変更します | admin |

### パーミッション

#### general

`easyresourcepack.command`

#### admin

`easyresourcepack.command.admin`

## コンフィグ - `config.yml`

サーバー起動後にコンフィグで変更を加えた場合、`/pack reload` を実行してください。

```yaml
# ---[ Default ]--------------------------------------
# 変更を反映するために /pack reload してください
# ----------------------------------------------------
# url: ""
# リソースパックのURL
# ----------------------------------------------------
# sha1: ""
# リソースパックのハッシュ値
# ----------------------------------------------------

url: ""
sha1: ""

use-join: true
use-plugin-message: false
```

### url

リソースパックのダウンロードURLを入力します。

### sha1

リソースパックのSHA-1をキャッシュしておくための設定であり、直接変更する必要はありません。ただし、`url` を変更した場合は、内容を `""` にしてください。"。

## 参加時の自動適用

```yaml
use-join: true
```

にしておく必要があります。

## プラグインメッセージ

```yaml
use-plugin-message: true
```

にしておく必要があります。

### `easyresourcepack:force`

指定したプレイヤーのパックを再読み込みする。

| データ | 種類 |
|-------|-----|
| playerName | UTF |
