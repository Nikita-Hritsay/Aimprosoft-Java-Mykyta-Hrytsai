const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack")

module.exports = {
    cache: false,
    entry: {
        index: "./src/js/index.ts",
    },
    output: {
        path: path.resolve(__dirname, "dist"),
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./src/html/index.html",
        }),
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        }),
    ],
    module: {
        rules: [
            { test: /\.tsx?$/, use: [ {loader: "ts-loader"}] },
            {
                test: /\.(eot|svg|ttf|woff|woff2|png|jpg|gif)$/i,
                type: "asset",
            },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
              },
        ],
    },
    resolve: {
        extensions: ['', '.js', '.ts', '.tsx', '.json'],
    },
}