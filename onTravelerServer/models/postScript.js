var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var postScriptSchema = new Schema({
    feed_id : {type : Schema.Types.ObjectId, require: true},
    customer_id : {type:String, require: true},
    grade : {type : Number, default : 0, min:0, max:5},
    comments : {type:String}
});

postScriptSchema.statics.create = function(data){
    var instance = new this(data);

    return instance.save();
};


postScriptSchema.statics.findAll = function(){
    return this.find({});
};

module.exports = mongoose.model('postScript', postScriptSchema);