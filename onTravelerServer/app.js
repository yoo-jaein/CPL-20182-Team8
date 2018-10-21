var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var mongoose = require('mongoose');


/* set routing */
var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var buddyRouter = require('./routes/buddies');
var checklistRouter = require('./routes/checklists');
var customerRouter = require('./routes/customers');
var feed_itemRouter = require('./routes/feed_items');
var schedule_itemRouter = require('./routes/schedule_items');
var imageRouter = require('./routes/images');


var app = express();

var mongo_server_url = "mongodb://localhost:27017/traveler";
var User = require('./models/user');


// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


/*  use routing   */
app.use('/', indexRouter);
app.use('/users', usersRouter);

app.use('/images', imageRouter);
app.use('/buddies', buddyRouter);
app.use('/checklists', checklistRouter);
app.use('/customers', customerRouter);
app.use('/feed_items', feed_itemRouter);
app.use('/schedule_items', schedule_itemRouter);


// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});


mongoose.connect(mongo_server_url, {useNewUrlParser: true});

var db = mongoose.connection;
db.on('error', console.error);
db.once('open', function(){
  console.log("Connected to mongod server");
});


module.exports = app;
