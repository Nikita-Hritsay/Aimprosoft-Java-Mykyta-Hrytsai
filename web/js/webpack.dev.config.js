const { merge } = require('webpack-merge');
const common = require('./webpack.common.config.js');

module.exports = merge(common,{
    mode: "development",
    devServer: {
        host: "localhost",
        port: 8081,
        proxy: {
            '/': 'http://localhost:8080/',
        },
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
        },
    }});


