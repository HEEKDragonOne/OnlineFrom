// vue.config.js
module.exports = {
    chainWebpack: config => {
        config
            .plugin('html')
            .tap(args => {
                args[0].title = '墨水记忆-在线报名系统'
                return args
            })
    }
}
