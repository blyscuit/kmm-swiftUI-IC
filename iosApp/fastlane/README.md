fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## iOS

### ios build_and_test

```sh
[bundle exec] fastlane ios build_and_test
```

Build and Test

### ios sync_development_signing

```sh
[bundle exec] fastlane ios sync_development_signing
```

Sync Development match signing

### ios sync_adhoc_signing

```sh
[bundle exec] fastlane ios sync_adhoc_signing
```

Sync Adhoc match signing

### ios sync_adhoc_production_signing

```sh
[bundle exec] fastlane ios sync_adhoc_production_signing
```

Sync Adhoc Production match signing

### ios sync_appstore_signing

```sh
[bundle exec] fastlane ios sync_appstore_signing
```

Sync AppStore match signing

### ios register_new_device

```sh
[bundle exec] fastlane ios register_new_device
```

Register new devices

### ios build_and_upload_staging_app

```sh
[bundle exec] fastlane ios build_and_upload_staging_app
```

Build and upload staging app to Firebase

### ios build_and_upload_production_app

```sh
[bundle exec] fastlane ios build_and_upload_production_app
```

Build and upload Production app to Firebase

### ios build_and_upload_appstore_app

```sh
[bundle exec] fastlane ios build_and_upload_appstore_app
```

Build and upload Production app to App Store

### ios clean_up

```sh
[bundle exec] fastlane ios clean_up
```

Clean up derived data

### ios clean_up_xcov

```sh
[bundle exec] fastlane ios clean_up_xcov
```

Clean up xcov output

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
