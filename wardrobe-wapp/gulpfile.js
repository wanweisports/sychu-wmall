const gulp = require('gulp');
const del = require('del');
const path = require('path');
const autoprefixer = require('gulp-autoprefixer');
const htmlmin = require('gulp-htmlmin');
const sass = require('gulp-sass');
const jsonminify = require('gulp-jsonminify2');
const gutil = require('gulp-util');
const combiner = require('stream-combiner2');
const babel = require('gulp-babel');
const uglify = require('gulp-uglify');
const rename = require("gulp-rename");
const runSequence = require('run-sequence');
const jsonlint = require("gulp-jsonlint");
const cleanCSS = require('gulp-clean-css');

let colors = gutil.colors;

const handleError = function (err) {
    console.log('\n');
    gutil.log(colors.red('Error!'));
    gutil.log('fileName: ' + colors.red(err.fileName));
    gutil.log('lineNumber: ' + colors.red(err.lineNumber));
    gutil.log('message: ' + err.message);
    gutil.log('plugin: ' + colors.yellow(err.plugin));
};

gulp.task('clean', function () {
    return del(['./build/**'])
});

gulp.task('watch', function () {
    gulp.watch('./src/**/*.json', ['json']);
    gulp.watch('./src/**/*.js', ['scripts']);
    gulp.watch('./src/**/*.wxml', ['templates']);
    gulp.watch('./src/**/*.wxss', ['wxss']);

    gulp.watch(['app.json', 'project.config.json'], ['appJSON']);
    gulp.watch('app.wxss', ['appWxss']);
    gulp.watch('app.js', ['appJS']);

    gulp.watch('./utils/**', ['copyUtils']);
    gulp.watch('./images/**', ['copyImages']);
});

gulp.task('jsonLint', function () {
    var combined = combiner.obj([
        gulp.src(['./src/**/*.json']),
        jsonlint(),
        jsonlint.reporter(),
        jsonlint.failAfterError()
    ]);

    combined.on('error', handleError);
});

gulp.task('json', ['jsonLint'], function () {
    return gulp.src('./src/**/*.json')
        .pipe(gulp.dest('./build/pages'));
});

gulp.task('jsonPro', ['jsonLint'], function () {
    return gulp.src('./src/**/*.json')
        .pipe(jsonminify())
        .pipe(gulp.dest('./build/pages'))
});

gulp.task('templates', function () {
    return gulp.src('./src/**/*.wxml')
        .pipe(gulp.dest('./build/pages'))
});

gulp.task('templatesPro', function () {
    return gulp.src('./src/**/*.wxml')
        .pipe(htmlmin({
            collapseWhitespace: true,
            removeComments: true,
            keepClosingSlash: true
        }))
        .pipe(gulp.dest('./build/pages'))
});

gulp.task('wxss', function () {
    return gulp.src('./src/**/*.wxss')
        .pipe(gulp.dest('./build/pages'))
});

gulp.task('wxssPro', function () {
    return gulp.src('./src/**/*.wxss')
        .pipe(cleanCSS())
        .pipe(gulp.dest('./build/pages'))
});

gulp.task('scripts', function () {
    return gulp.src('./src/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(gulp.dest('./build/pages'))
});

gulp.task('scriptsPro', function () {
    return gulp.src('./src/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(uglify({
            compress: true,
        }))
        .pipe(gulp.dest('./build/pages'))
});

/*******/

gulp.task('appJSON', ['jsonLint'], function () {
    return gulp.src(['app.json', 'project.config.json'])
        .pipe(gulp.dest('./build'));
});

gulp.task('appJSONPro', ['jsonLint'], function () {
    return gulp.src(['app.json', 'project.config.json'])
        .pipe(jsonminify())
        .pipe(gulp.dest('./build'))
});

gulp.task('appWxss', function () {
    return gulp.src('app.wxss')
        .pipe(gulp.dest('./build'))
});

gulp.task('appWxssPro', function () {
    return gulp.src('app.wxss')
        .pipe(cleanCSS())
        .pipe(gulp.dest('./build'))
});

gulp.task('appJS', function () {
    return gulp.src('app.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(gulp.dest('./build'))
});

gulp.task('appJSPro', function () {
    return gulp.src(['app.js'])
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(uglify({
            compress: true,
        }))
        .pipe(gulp.dest('./build'))
});

gulp.task('appJSTest', function () {
    return gulp.src(['testApp.js'])
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(uglify({
            compress: true,
        }))
        .pipe(rename("app.js"))
        .pipe(gulp.dest('./build'))
});

/*******/

gulp.task('copyUtils', function () {
    return gulp.src(['./utils/**'])
        .pipe(gulp.dest('./build/utils'))
});

gulp.task('copyImages', function () {
    return gulp.src(['./images/**'])
        .pipe(gulp.dest('./build/images'))
});

gulp.task('dev', ['clean'], function () {
    runSequence('json', 'templates', 'wxss', 'scripts', 'appJSON', 'appWxss', 'appJS', 'copyUtils', 'copyImages', 'watch');
});

gulp.task('build', ['jsonPro', 'templatesPro', 'wxssPro', 'scriptsPro', 'appJSONPro', 'appWxssPro', 'appJSPro', 'copyUtils', 'copyImages']);

gulp.task('pro', ['clean'], function () {
    runSequence('build');
});

gulp.task('testBuild', ['jsonPro', 'templatesPro', 'wxssPro', 'scriptsPro', 'appJSONPro', 'appWxssPro', 'appJSTest', 'copyUtils', 'copyImages']);

gulp.task('test', ['clean'], function () {
    runSequence('testBuild');
});
