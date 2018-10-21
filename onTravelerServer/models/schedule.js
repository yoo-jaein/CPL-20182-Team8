var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var scheduleSchema = new Schema({
    start_time : Date,
    end_time : Date,
    schedule_type : {type:String},
    name : {type:String},
    location : {type:String},
    checklist_id : {type:Schema.Types.ObjectId}
});

scheduleSchema.statics.create = function(data){
    var instance = new this(data);

    return instance.save();
};


scheduleSchema.statics.findAll = function(){
    return this.find({});
};

module.exports = mongoose.model('schedule', scheduleSchema);