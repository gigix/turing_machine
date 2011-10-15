ROOT_DIR=$(dirname $0)

pushd $ROOT_DIR/domain && \
./build.sh &&
popd && \
\
pushd $ROOT_DIR/web && \
./build.sh