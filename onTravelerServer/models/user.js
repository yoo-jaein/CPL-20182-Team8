var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var userSchema = new Schema({
    name : String,
    money : Number
})

module.exports = mongoose.model('user', userSchema);