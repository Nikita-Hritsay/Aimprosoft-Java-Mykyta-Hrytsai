const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
    entry: [
        './src/index.js',
    ],
    output: {
        filename: './bundle.js'
    },
    plugins: [
        new HtmlWebpackPlugin(),
    ],
    devServer: {
        port: 8081,
        hot: true,
        open: true
    },

}