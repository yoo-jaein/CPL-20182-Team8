var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var buddySchema = new Schema({
    //_id : { type : String, required : true , unique: true},
    pw : { type : String, required : true , trim : true},
    name : { type : String, required : true },
    active_location : String,
    notification : String,
    price_list : [
        {
            name : {type:String, required : true},
            money : {type : Number, required : true, min : 0},
        }
    ],
    feed_item_id_list : [ Schema.Types.ObjectId ],
    checklist_id_list : [ Schema.Types.ObjectId ],
    schedule_item_id_list : [Schema.Types.ObjectId],
    grade : {type : Number, default : 0, min:0},
    post_script : [String],
    chat_id_list : [ Schema.Types.ObjectId ]
});


buddySchema.statics.create = function(data){
    var instance = new this(data);

    return instance.save();
};


buddySchema.statics.findAll = function(){
    return this.find({});
};


module.exports = mongoose.model('buddy', buddySchema);