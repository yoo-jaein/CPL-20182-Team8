var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var buddySchema = new Schema({
    buddy_id : { type : String, required : true , unique: true},
    name : { type : String, required : true },
    location_name : String,
    active_location : {
        latitude : Number,
        longitude : Number
    },
    notification : String,
    price_list : [
        {
            name : {type:String, required : true},
            money : {type : Number, required : true, min : 0},
        }
    ],
    hashtag : [String]
});


buddySchema.statics.create = function(data){
    var instance = new this(data);

    return instance.save();
};


buddySchema.statics.findAll = function(){
    return this.find({});
};


module.exports = mongoose.model('buddy', buddySchema);