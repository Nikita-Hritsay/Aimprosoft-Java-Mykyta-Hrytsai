const { merge } = require('webpack-merge');
const common = require('./webpack.common.config.js');

module.exports = merge(common,{
    mode: "development",
    devServer: {
        open: true,
        host: "localhost",
        port: 8081,
        proxy: {
            '/api': {
                target: 'ws://localhost:8080',
                ws: true,
                },
            },
        },
    });


