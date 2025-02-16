# Magic Soup Plugin

A Minecraft Spigot plugin that adds a magical soup with special effects and a dynamic boss bar display.

## Features

- 🍜 Custom soup recipe with unique ingredients
- ⚡ Special effects upon consumption
- 📊 Dynamic boss bar display showing effect duration
- ⚙️ Fully configurable through config.yml
- 🔒 Permission-based access control
- 🎮 Simple and intuitive commands

## Requirements

- Minecraft Server (Spigot) 1.21.4
- Java 17 or higher

## Installation

1. Download the latest release from the releases page
2. Place the .jar file in your server's `plugins` folder
3. Restart your server
4. Configure the plugin in `plugins/MagicSoup/config.yml`

## Recipe

The magical soup requires:
- 1 Beetroot Soup
- 1 Redstone Block
- 1 Glowstone 
- 1 Lapis Lazuli
- 1 Water Bucket

## Commands

- `/magicsoup give [player]` - Give magical soup to a player
- `/magicsoup reload` - Reload the plugin configuration
- `/magicsoup recipe` - Display the crafting recipe

## Permissions

- `magicsoup.use` - Allows players to use the magical soup
- `magicsoup.create` - Allows players to craft the magical soup
- `magicsoup.admin` - Grants access to administrative commands

## Configuration

The plugin is highly configurable through `config.yml`. You can modify:
- Configure Settings
  - Effect duration
  - Boss bar colors and style
  - Permission nodes
    - `use`
    - `create`
    - `admin`
  - Messages

## Building from Source

1. Clone the repository
2. Install Maven
3. Run `mvn clean package`
4. Find the compiled jar in `target/`

## Contributing

1. Fork the repository
2. Create a new branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the GNU General Public License v3.0 - see the LICENSE file for details.

## Support

For support:
1. Check the [Wiki](https://github.com/TheCaptainCook/MagicSoup/wiki)
2. Open an [Issue](https://github.com/TheCaptainCook/MagicSoup/issues)
3. Join our Discord server (Work in progress)

## Authors

- Sheikh Masem Mandal (@TheCaptainCook)

## Acknowledgments

- Spigot API Team
- Minecraft Community
- All contributors