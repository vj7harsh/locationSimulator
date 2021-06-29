# About Application
This application can be used to get equidistant(50m) points between the origin and destination along the path shown in google maps.

The application has 3 components and 1 utility class
Controller: Landing page (scope: request and response)
Manager: Contains business logic of the operation (picking out points from given set of points from helper)
Helper: Communicate with Google maps (making calls to google and transform and forward the required feilds to manager )
Utility : Contains geometric/mathematical methods(static)

# Sample request and response 

* https://docs.google.com/document/d/1WI0tzB5uoNY_MxVHf6WDsWWd1SaFJQeuKeHAhb4dxkE/edit?usp=sharing

curl --location --request POST 'http://localhost:8080/route/directions' \
--header 'Content-Type: application/json' \
--data-raw '{
    "origin": {
        "lat": "12.912710",
        "lng": "77.710510"
    },
    "destination": {
        "lat": "12.915628",
        "lng": "77.721958"
    }
}'


{
    "pathPoints": [
        {
            "lat": 12.912600000000001,
            "lng": 77.71053
        },
        {
            "lat": 12.912666711674495,
            "lng": 77.71098622177396
        },
        .
        .
        .
        .
        {
            "lat": 12.915672373881796,
            "lng": 77.72154100894565
        }
    ]
}

# Starting Application
The application can be started from LocationSimulatorApplication.class (main method).

