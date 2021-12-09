const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack")
const ESLintPlugin = require('eslint-webpack-plugin');

module.exports = {
    cache: false,
    entry: {
        index: "./src/js/index.ts",
    },
    output: {
        path: path.resolve(__dirname, 'target/webpack/dist'),
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./src/html/index.html",
        }),
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        }),
        new ESLintPlugin()
    ],
    module: {
        rules: [
            { test: /\.tsx?$/, use: [ {loader: "ts-loader"}] },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'eslint-loader',
                options: {
                // eslint options (if necessary)
                },
            }


        ],
    },
    resolve: {
        extensions: ['', '.js', '.ts', '.tsx', '.json'],
    },
}