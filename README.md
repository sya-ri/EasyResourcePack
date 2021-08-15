# EasyResourcePack

## コマンド

| コマンド名 | 説明 | パーミッション |
|----------|------|-------------|
| `/pack` | リソースパックを再度適用します | `easyresourcepack.command` |
| `/pack reload` | コンフィグをリロードします | `easyresourcepack.command.admin` |
| `/pack get` | 現在の設定を確認します | `easyresourcepack.command.admin` |
| `/pack set` | リソースパックのURLを変更します | `easyresourcepack.command.admin` |
| `/pack force` | 指定したプレイヤーのリソースパックを再度適用します | `easyresourcepack.command.admin` |

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
```

### url

リソースパックのダウンロードURLを入力します。

### sha1

リソースパックのSHA-1をキャッシュしておくための設定であり、直接変更する必要はありません。ただし、`url` を変更した場合は、内容を `""` にしてください。"。
