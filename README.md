# Scalar Grin Android Wallet

## Table of Contents
- [Scalar Grin Android Wallet](#Scalar-Grin-Android-Wallet)
  - [Table of Contents](#Table-of-Contents)
  - [Introduction](#Introduction)
  - [Features](#Features)
  - [Feedback](#Feedback)
  - [Development](#Development)
    - [Getting Started](#Getting-Started)
    - [Building the Project](#Building-the-Project)
    - [Troubleshooting](#Troubleshooting)
      - [Build Errors](#Build-Errors)

## Introduction
Scalar is a private Grin wallet that allows you to easily create or access an account 
with a recovery phrase. Currently, the state of sending transactions is a major headache.
We are working hard to make sending and receiving transations in a single action,
with Auto-receive.

## Features

A few of the things the wallet will support:

* Send and receive Grin
* Create and recover a new wallet with a recovery phrase
* PIN and fingerprint security
* Full transparency and control on what is stored on the device

## Feedback

Feel free to send us feedback by [filing an issue](https://github.com/block-equity/stellar-android-wallet/issues/new). 
<!-- on [Twitter](https://twitter.com/TODO:Twitter handle) or  -->


<!-- Feature requests are always welcome. If you wish to contribute, please take a quick look at the [guidelines](./CONTRIBUTING.md)!

If you just want to hang out and chat about BlockEQ, please feel free to join our [Discord Channel](TODO:Add Discord link)! -->

## Development

Development is ongoing, we are working hard in our non-working hours free time.
This is a very cool and fun project for us and we are excited to make it happen!

### Getting Started
* Install [Android Studio](https://developer.android.com/studio) :heart:

### Building the Project

OS X, Windows & Linux:

```sh
git clone https://github.com/grin-wallet/scalar.git
```

1. `cd` into the project repo
2. Import the project. Open Android Studio, click `Open an existing Android
   Studio project` and select the project. Gradle will build the project.
3. Run the app. Click `Run > Run 'app'`. After the project builds you'll be
   prompted to build or launch an emulator.


If the above steps completed successfully, you're ready to begin developing! Otherwise, 
check out the troubleshooting section below.

### Troubleshooting

#### Build Errors
These are difficult to predict ahead of time, but general build error fixes include:
* Peforming a clean build
* `File > Invalidate Caches/ Restart`

If you still are having issues, an upstream dependency may have caused build errors, or 
there might be something specific to your environment. Feel free to open an issue if you 
find yourself in this situation.