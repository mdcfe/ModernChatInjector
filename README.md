# ModernChatInjector

A drop-in replacement for ChatInjector, now with built-in support for EssentialsX Chat 2.21.0, as well as older
versions.

## Usage

Install ModernChatInjector alongside PlaceholderAPI, any expansions you need, and your chat formatter of choice.  
Then, add PlaceholderAPI placeholders within your chat formatting plugin using the following syntax:
`{placeholder_name}`.

Note that you need to use `{` and `}` around the placeholders, *not* `%` symbols like you might expect! 

## How does it work?

On EssentialsX 2.20.0 and above, ModernChatInjector hooks EssentialsX Chat's own chat events to ensure PlaceholderAPI
placeholders are injected into the message format. 
This is necessary as the previous versions of ChatInjector relied on Bukkit events and protocol hacks, which aren't
fully compatible with current versions of EssentialsX Chat or Paper chat events.

If EssentialsX Chat isn't installed or if you're running older versions of Spigot or Paper, this plugin will fall back
to hooking Bukkit's `AsyncPlayerChatEvent` (as ChatInjector does), ensuring compatibility with other legacy chat
formatters.

## Why isn't this built into EssentialsX Chat?
This provides support for PlaceholderAPI in "legacy" style chat formatting plugins, like EssentialsX Chat and
VaultChatFormatter, in the same way ChatInjector used to work.

We're planning a larger overhaul of EssentialsX Chat in a future version, which will include support for external
placeholders. Until that's ready, we don't want to guarantee support for a specific PAPI format that could change in the
future, as all server owners would then need to reconfigure their chat formats a second time.
