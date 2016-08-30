
//web application context path for static resources
var _ctx =  $("meta[name='ctx']").attr("content");

var PAIR_COUNT = 18;
var ROWS = 6;
var COLS = 6;
var DROPCARD_TIMEOUT = 400;  //milliseconds
var HIDECARD_TIMEOUT = 700;  // milliseconds

$(document).ready(function(){
    $(".cardholder").click(function(){
        var img = $(this).find("img");
        var imgNumber = img.attr("name");
        if (img.hasClass("card-active") && openCardsCount < 2) {
            clickedCount++;
            clickedCards[clickedCount - 1] = imgNumber;
            img.attr("src", _ctx + "/resources/img/Image" + numberMap[imgNumber] + ".jpg");
            img.removeClass("card-active");
            openCardsCount++;
            if (clickedCount > 1) {
                clickedCount = 0;
                checkcards();
            }
        }
    });
});


//TODO build table by appending rows in tables
function buildgame(){
    var rowCounter;
    var colCounter;

    var table = function() {
        document.write('<tr>');
        document.write('<td colspan="6"><div id="timer" class="centerize">--:--</div></td>');
        document.write('</tr>');
        for (rowCounter = 0; rowCounter <= ROWS - 1; rowCounter++) {
            document.write('<tr>');
            for (colCounter = 0; colCounter <= COLS - 1; colCounter++) {
                document.write('<td class="cardholder">');
                document.write('<img class="card" src="' + _ctx + '/resources/img/back.jpg" name="' + ((rowCounter * COLS) + colCounter) + '">');
                document.write('</td>');
            }
            document.write('</tr>');
        }

    document.write('<tr>');
    document.write('<td colspan="6" id="new-game-container" class="centerize "><button id="newgame-but" onclick="startgame()">Start game</button></td>');
    document.write('</tr>');
    };
    $('.container').append(table());

    var h = $(document.createElement('div')).attr('id', 'starter-label');
    $('#table-game').find('tbody').append(h);
    var p = $(document.createElement('p')).text("press button to start new game");
    $('#starter-label').append(p);
}



var numberMap = newUnscrMap(PAIR_COUNT);
var activegame = false;
var openCardsCount;
var clickedCards;
var clickedCount;
var progress;
var timer;
var ticker;
var initDate;

function newUnscrMap(pairCount) {
    var array = [];
    for (var i = 0; i < pairCount; i++) {
        array[i] = i;
        array[pairCount*2-1-i] = i;
    }
    return array;
}

function arrayShuffle(a) {
    var d,
        c,
        b = a.length;
    while (b) {
        c = Math.floor(Math.random() * b);
        d = a[--b];
        a[b] = a[c];
        a[c] = d
    }
    return a;
}

function togglecardsactive() {
    for (var i = 0; i < PAIR_COUNT * 2; i++) {
        $("[name='" + i + "']").toggleClass('card-active');
    }
}

function setcardsinactive() {
    for (var i = 0; i < PAIR_COUNT * 2; i++) {
        $("[name='" + i + "']").removeClass('card-active');
    }
}

function startgame() {
    setcardback();
    setcardsinactive();
    togglecardsactive();
    numberMap = arrayShuffle(newUnscrMap(PAIR_COUNT));
    resettimer();
    clickedCards = [-1,-1];
    clickedCount = 0;
    openCardsCount = 0;
    progress = 0;
    activegame = true;
    setTimeout(function(){tick();}, 1000);
}

function setcardback() {
    for (var i = 0; i < PAIR_COUNT * 2; i++) {
        $("[name='" + i + "']").attr("src", _ctx + "/resources/img/back.jpg");
    }
}

function checkcards() {
    if (numberMap[clickedCards[0]] == numberMap[clickedCards[1]]) {
        progress++;
        setTimeout(dropcard.bind(null, clickedCards[0]), DROPCARD_TIMEOUT);
        setTimeout(dropcard.bind(null, clickedCards[1]), DROPCARD_TIMEOUT);
    } else {
        setTimeout(hidecard.bind(null, clickedCards[0]), HIDECARD_TIMEOUT);
        setTimeout(hidecard.bind(null, clickedCards[1]), HIDECARD_TIMEOUT);
    }
    clickedCards[0] = -1;
    clickedCards[1] = -1;
    if (progress == PAIR_COUNT) {
        finishgame();
    }
}

function resettimer() {
    initDate = new Date();
    timer = 0;
    $('#timer').text("00:00");
}

function hidecard(num) {
    var img = $('[name="'+num+'"]');
    img.attr("src", _ctx + "/resources/img/back.jpg");
    img.addClass('card-active');
    openCardsCount--;
}

function dropcard(num) {
    $('[name="'+num+'"]').attr("src", _ctx + "/resources/img/cap.jpg");
    //$('[name="'+num+'"]').toggleClass('card-active');
    openCardsCount--;
}

function tick() {
    if (activegame) {
        timer = Math.floor((new Date() - initDate) / 1000);
        var min = integerDivision(timer, 60);
        var sec = timer % 60;
        $('#timer').text(((min < 10) ? "0" + min : min) + ":" + ((sec < 10) ? "0" + sec : sec));
        ticker = setTimeout(function () {
            tick();
        }, 1000);
    }
}

function integerDivision(x, y){
    return (x-x%y)/y
}


function finishgame() {
    clearTimeout(ticker);
    activegame = false;
    sendResult(timer);
    setTimeout(function () {
        getTopResults();
    }, 200);
}

var sendResult = function (result) {
    $.post(
        _ctx + "/result/add?gameResult=" + result,
        { gameResult : result},
        function(data){
            changeBestResult(data);
            getTopResults();
        });
};

/*
$(document).ready(function () {
    $("#top-result-list").click(function () {
        $.ajax({
            url:_ctx + "/result/add?gameResult=" + timer,
            type:"POST",
            data: {},
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function (data) {
                alert(data);
            }
        })
    });
});
*/



$(document).ready(function () {
    getTopResults();
});

function getTopResults() {
    $.getJSON(
        _ctx + "/result/gettopresults",
        {},
        function (data) {
            var parsed = JSON.stringify(data);
            if (parsed != "[]") {
                var list = $('#top-result-list');
                list.html("");
                for (var i = 0; i < data.length; i++) {
                    var h = $(document.createElement('li')).text(data[i].result + "  --  " + data[i].user);
                    list.append(h);
                }
            } else {
                $('#top-result-list').html("no results to show");
            }
        });
}

function changeBestResult(result) {
    if (result != 0) {
        $('#best-result').html(", your best - " + result + " sec");
    }
    else {
        $('#best-result').html("");
    }
}

function getBestUserResult() {
    $.getJSON(
        _ctx + "/result/getbestresult",
        {},
        function (data) {
            changeBestResult(data);
        }
    )
}

$(document).ready(function () {
    getBestUserResult();
});
$(document).ready(function () {
    $('#starter-label').hover( function () {
        $('#starter-label').animate(
            {
                "height" : "0%",
                "width" : "20%",
                "opacity" : "0",
                "top" : "100%",
                "left" : "40%",
                "font-size" : "1"
            }, 800, function () {
                $(this).hide();
            });
    });
});