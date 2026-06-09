# Nakasyou Bakery Mod

Minecraft `26.1.2` 向けのFabricサーバーModです。

## 挙動

- [nakasyou](https://github.com/nakasyou) がクラフトしたパンは 「[nakasyou](https://github.com/nakasyou) bakeryのパン」 になり、愛情たっぷりの説明文が付きます。
- 村人取引で入手したパンは `Not fair trade bread` になり、かなり不穏な説明文が付きます。
- [nakasyou](https://github.com/nakasyou) bakeryのパンを食べると、20% の確率で不死のトーテムの演出と効果（再生能力 II・衝撃吸収 II・火炎耐性）が発動します。
- [nakasyou](https://github.com/nakasyou) 以外が作ったパンを食べると、腐肉と同じ確率で空腹になります。
- 村人取引パンは、たとえ [nakasyou](https://github.com/nakasyou) が買っても搾取パンとして扱われます。

つまり最終仕様は、[nakasyou](https://github.com/nakasyou) さんの手作りパンは愛。村人取引パンは搾取。

最低すぎる経済圏で草。

## GitHub Actions成果物

`Build` ワークフローで `gradle build --no-daemon` を実行し、`build/libs/*.jar` を `nakasyou-bakery-mod` としてアップロードします。
