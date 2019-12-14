function myTime(date){
    var arr=date.split("T");
    var d=arr[0];
    var darr = d.split('-');

    var t=arr[1];
    var tarr = t.split('.000');
    var marr = tarr[0].split(':');

    var dd = parseInt(darr[0])+"-"+parseInt(darr[1])+"-"+parseInt(darr[2]);
    //+" "+parseInt(marr[0])+":"+parseInt(marr[1])+":"+parseInt(marr[2])
    return dd;
}

function showTime(tempDate)
{
    var d = new Date(tempDate);
    var year = d.getFullYear();
    var month = d.getMonth();
    month++;
    var day = d.getDate();
    var hours = d.getHours();

    var minutes = d.getMinutes();
    var seconds = d.getSeconds();
    month = month<10 ? "0"+month:month;
    day = day<10 ? "0"+day:day;
    hours = hours<10 ? "0"+hours:hours;
    minutes = minutes<10 ? "0"+minutes:minutes;
    seconds = seconds<10 ? "0"+seconds:seconds;


    var time = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    return time;
}
