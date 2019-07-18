echo "Starting Compilation..."
echo ""

cd rust-android-plugin/rust

echo "Compiling ARM 64..."
cargo build --target aarch64-linux-android --release
echo ""

echo "Compiling ARM v7..."
# cargo build --target armv7-linux-androideabi --release
echo ""

echo "Compiling x86..."
# cargo build --target i686-linux-android --release
echo ""

echo "Copying libraries into Android Studio project..."
cp -r "./target/aarch64-linux-android/release/libGrinWalletAndroidAPI.so" "../../../app/src/main/jniLibs/arm64-v8a/"
cp -r "./target/armv7-linux-androideabi/release/libGrinWalletAndroidAPI.so" "../../../app/src/main/jniLibs/armeabi-v7a/"
cp -r "./target/i686-linux-android/release/libGrinWalletAndroidAPI.so" "../../../app/src/main/jniLibs/x86/"

cd ../..

echo "Done!"