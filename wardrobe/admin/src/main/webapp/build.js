({
    appDir: './',   //项目根目录
    dir: './build',  //输出目录，全部文件打包后要放入的文件夹（如果没有会自动新建的）

    baseUrl: './Content/js/app',   //相对于appDir，代表要查找js文件的起始文件夹，下文所有文件路径的定义都是基于这个baseUrl的

    modules: [					  //要优化的模块
        { name:'dashboard/index'}	//说白了就是各页面的入口文件，相对baseUrl的路径，也是省略后缀“.js”
    ],

    fileExclusionRegExp: /^(r|build)\.js|.*\.scss$/,	//过滤，匹配到的文件将不会被输出到输出目录去

    optimizeCss: 'standard',

    removeCombined: false,   //如果为true，将从输出目录中删除已合并的文件

    paths: {
        "jquery"    : '../../bower_components/jquery/dist/jquery.min',
        "tether"    : '../../bower_components/tether/dist/js/tether.min',
        "bootstrap" : '../../bower_components/bootstrap/dist/js/bootstrap.min',
        "pace"      : '../../bower_components/pace/pace.min',
        "chart"     : '../../bower_components/chart.js/dist/Chart.min',

        "base"      : '../widgets/common/base'
    }
})
