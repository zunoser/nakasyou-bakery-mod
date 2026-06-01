# Nakasyou Bakery Mod

Fabric server mod for Minecraft `26.1.2`.

## Behavior

- Bread crafted by `nakasyou0` is renamed to `nakasyou bakeryのパン` and gets bakery lore.
- Bread taken from a villager trade result slot is renamed to `Not fair trade bread` and gets not-fair-trade lore.
- Villager-traded bread takes precedence, even when the buyer is `nakasyou0`.

## GitHub Actions Artifact

The `Build` workflow runs `gradle build --no-daemon` and uploads `build/libs/*.jar` as the `nakasyou-bakery-mod` artifact.
