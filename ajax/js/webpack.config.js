const path = require("path");
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: [
        './src/index.js',
    ],
    output: {
        filename: './bundle.js',
        path: path.resolve(__dirname, './dist')
    },
    plugins: [new HtmlWebpackPlugin({
        filename: 'index.html',
        template: 'src/index.html'
    })],
    devServer: {
        static: {
            directory: path.resolve(__dirname, '/dist'),
        },
        compress: true,
        port: 8081,
    },
}