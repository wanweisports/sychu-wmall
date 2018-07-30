#!/bin/bash

set -e

echo "env.namespace = $NAMESPACE"
echo "env.app_name = $APP_NAME"

if [ -z $NAMESPACE ]; then
    echo "NAMESPACE is null."
    exit 1
elif [ "$NAMESPACE" != "dev" ] && [ "$APP_NAME" == "loan" ]; then

    if command -v npm >/dev/null 2>&1; then

        echo "start to compile the frontend resource"
        export DISABLE_NOTIFIER=true
        cd src/main/webapp
        #解压之前的node modules
        if [ -f "node_modules.tar.gz" ]; then
            tar -zxf node_modules.tar.gz
        fi
        npm install > /dev/null
        ./node_modules/gulp/bin/gulp.js clean
        ./node_modules/gulp/bin/gulp.js build
        echo "end to compile the frontend resource"
        echo 'start zip node_modules...'
        tar -zcf node_modules.tar.gz ./node_modules
        echo 'end zip node_modules'
        rm -rf ./node_modules
        echo "remove node_modules done"
    else
        echo "npm is not install"
    fi
else
    echo "Won't compile the frontend resources in dev mod."
fi