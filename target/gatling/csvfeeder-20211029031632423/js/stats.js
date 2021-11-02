var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "10",
        "ok": "4",
        "ko": "6"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "4",
        "ko": "4"
    },
    "maxResponseTime": {
        "total": "3649",
        "ok": "3649",
        "ko": "6"
    },
    "meanResponseTime": {
        "total": "369",
        "ok": "916",
        "ko": "5"
    },
    "standardDeviation": {
        "total": "1093",
        "ok": "1578",
        "ko": "1"
    },
    "percentiles1": {
        "total": "4",
        "ok": "5",
        "ko": "4"
    },
    "percentiles2": {
        "total": "5",
        "ok": "916",
        "ko": "5"
    },
    "percentiles3": {
        "total": "2010",
        "ok": "3102",
        "ko": "6"
    },
    "percentiles4": {
        "total": "3321",
        "ok": "3540",
        "ko": "6"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 3,
    "percentage": 30
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1,
    "percentage": 10
},
    "group4": {
    "name": "failed",
    "count": 6,
    "percentage": 60
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.714",
        "ok": "0.286",
        "ko": "0.429"
    }
},
contents: {
"req_get-a-specific--4c9b6": {
        type: "REQUEST",
        name: "Get a specific video game",
path: "Get a specific video game",
pathFormatted: "req_get-a-specific--4c9b6",
stats: {
    "name": "Get a specific video game",
    "numberOfRequests": {
        "total": "10",
        "ok": "4",
        "ko": "6"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "4",
        "ko": "4"
    },
    "maxResponseTime": {
        "total": "3649",
        "ok": "3649",
        "ko": "6"
    },
    "meanResponseTime": {
        "total": "369",
        "ok": "916",
        "ko": "5"
    },
    "standardDeviation": {
        "total": "1093",
        "ok": "1578",
        "ko": "1"
    },
    "percentiles1": {
        "total": "4",
        "ok": "5",
        "ko": "4"
    },
    "percentiles2": {
        "total": "5",
        "ok": "916",
        "ko": "5"
    },
    "percentiles3": {
        "total": "2010",
        "ok": "3102",
        "ko": "6"
    },
    "percentiles4": {
        "total": "3321",
        "ok": "3540",
        "ko": "6"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 3,
    "percentage": 30
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1,
    "percentage": 10
},
    "group4": {
    "name": "failed",
    "count": 6,
    "percentage": 60
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.714",
        "ok": "0.286",
        "ko": "0.429"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
