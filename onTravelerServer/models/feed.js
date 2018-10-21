var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var feedSchema = new Schema({
    favorite_num : { type:Number, min : 0},
    comment_list : [String],
    picture_taken_location : {type:String},
    hashtag : [ String ],
    buddy_id : {type:String, require:true}
});

feedSchema.statics.create = function(data){
    var instance = new this(data);

    return instance.save();
};


feedSchema.statics.findAll = function(){
    return this.find({});
};

module.exports = mongoose.model('feed', feedSchema);