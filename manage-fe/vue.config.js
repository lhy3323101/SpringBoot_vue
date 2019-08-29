module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    productionSourceMap: false,
    lintOnSave: false,
    // devServer: {
    //     proxy: {
    //         '/api':{
    //             target:'http://jsonplaceholder.typicode.com',
    //             changeOrigin:true,
    //             pathRewrite:{
    //                 '/api':''
    //             }
    //         }
    //     }
    // }
    devServer: {
        port: 8080, // 端口号
        host: 'localhost',
        https: false, // https:{type:Boolean}
        open: true, //配置自动启动浏览器
        proxy: {
            '/test': {
                target: 'http://localhost:8081/',
                changeOrigin: true,
                pathRewrite: {
                    '^/test/': ''
                }
            }
        },
    }
}
