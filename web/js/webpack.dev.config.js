const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack")

module.exports = {
    entry: "./src/index.js",
    mode: "development",
    output: {
      path: path.resolve(__dirname, "dist"),
    },
    devServer: {
      open: true,
      host: "localhost",
      port: 8081,
        proxy: {
            '/websocket': {
                target: 'ws://localhost:8081',
                ws: true,
                secure: true
            },
        },
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: "./src/index.html",
      }),
      new webpack.ProvidePlugin({
        $: 'jquery',
        jQuery: 'jquery'
      }),
    ],
    module: {
      rules: [
        {
          test: /\.(ts|tsx)$/i,
          loader: "ts-loader",
          exclude: ["/node_modules/"],
        },
        {
          test: /\.(eot|svg|ttf|woff|woff2|png|jpg|gif)$/i,
          type: "asset",
        },
      ],
    },
    resolve: {
      extensions: [".tsx", ".ts", ".js"],
    },
  }



