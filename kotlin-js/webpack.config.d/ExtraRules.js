/*
 * Fine grained control over webpack config
 */

/**
 * We might need this to import css at some point, but
 * things are working without it for now...
 *
 * NOTE: as seen in
 *   https://discuss.kotlinlang.org/t/kotlin-js-a-style-from-the-npm-package/16759/2
 *   https://discuss.kotlinlang.org/t/how-to-import-css-in-jotlin-js/16762/4
 *   https://youtrack.jetbrains.com/issue/KT-32721?_ga=2.244652485.135295330.1611062932-1983262014.1601984825
 */
/*
 config.module.rules.push({
    test: /\.css$/,
        use: ["style-loader", "css-loader"]
});
*/

config.module.rules.push({
    test: /\.(png|svg|jpg|gif)$/,
    loader: "file-loader",
    options:  {
        outputPath: 'images'
    }
});