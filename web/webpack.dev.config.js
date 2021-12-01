const { merge } = require('webpack-merge');
const common = require('./webpack.common.config.js');

module.exports = merge(common,{
    mode: "development",
    devServer: {
        port: 8081,
        proxy: {
            '/': 'http://localhost:8080'
        }, 
        hot: true,
        client: {
            reconnect: true
        },
        compress: true
    }});


