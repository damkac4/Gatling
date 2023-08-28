package simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


/**
 * This sample is based on our official tutorials:
 * <ul>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/quickstart">Gatling quickstart tutorial</a>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/advanced">Gatling advanced tutorial</a>
 * </ul>
 */
public class Simulation extends io.gatling.javaapi.core.Simulation {

    //OBSERVATIONAL STATIONS
    ChainBuilder chain =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                    http("Postgres_OS")
                            .get("/observationalStation/findNearbyPlacesPostgres")
                            .queryParam("latitude", 52.0)
                            .queryParam("longitude", 18.0)
                            .check(
                                    status().is(200)
                            )
            ).pause(1)
            );
    ChainBuilder chain2 =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                            http("Mongo_OS")
                                    .get("/observationalStation/findNearbyPlacesMongo")
                                    .queryParam("latitude", 52.0)
                                    .queryParam("longitude", 18.0)
                                    .check(
                                            status().is(200)
                                    )
                    ).pause(1)
            );
    ChainBuilder chain3 =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                            http("Postgres2_OS")
                                    .get("/observationalStation/findNearbyPlacesPostgres2")
                                    .queryParam("latitude", 52.0)
                                    .queryParam("longitude", 18.0)
                                    .queryParam("maxDistance", 500.0)
                                    .check(
                                            status().is(200)
                                    )
                    ).pause(1)
            );
    ChainBuilder chain4 =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                            http("Mongo2_OS")
                                    .get("/observationalStation/findNearbyPlacesMongo2")
                                    .queryParam("latitude", 52.0)
                                    .queryParam("longitude", 18.0)
                                    .queryParam("maxDistance", 500.0)
                                    .check(
                                            status().is(200)
                                    )
                    ).pause(1)
            );
    //FORECAST STATIONS


    ChainBuilder chain5 =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                            http("Postgres_FS")
                                    .get("/forecastStation/findNearbyPlacesPostgres")
                                    .queryParam("latitude", 52.0)
                                    .queryParam("longitude", 18.0)
                                    .check(
                                            status().is(200)
                                    )
                    ).pause(1)
            );
    ChainBuilder chain6 =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                            http("Mongo_FS")
                                    .get("/forecastStation/findNearbyPlacesMongo")
                                    .queryParam("latitude", 52.0)
                                    .queryParam("longitude", 18.0)
                                    .check(
                                            status().is(200)
                                    )
                    ).pause(1)
            );
    ChainBuilder chain7 =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                            http("Postgres2_FS")
                                    .get("/forecastStation/findNearbyPlacesPostgres2")
                                    .queryParam("latitude", 52.0)
                                    .queryParam("longitude", 18.0)
                                    .queryParam("maxDistance", 500.0)
                                    .check(
                                            status().is(200)
                                    )
                    ).pause(1)
            );
    ChainBuilder chain8 =
            // Note how we force the counter name, so we can reuse it
            repeat(5).on(
                    exec(
                            http("Mongo2_FS")
                                    .get("/forecastStation/findNearbyPlacesMongo2")
                                    .queryParam("latitude", 52.0)
                                    .queryParam("longitude", 18.0)
                                    .queryParam("maxDistance", 500.0)
                                    .check(
                                            status().is(200)
                                    )
                    ).pause(1)
            );


    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8080");

    ScenarioBuilder scenario = scenario("Postgres_OS").exec(chain);
    ScenarioBuilder scenario2 = scenario("Mongo_OS").exec(chain2);
    ScenarioBuilder scenario3 = scenario("Postgres2_OS").exec(chain3);
    ScenarioBuilder scenario4 = scenario("Mongo2_OS").exec(chain4);

    ScenarioBuilder scenario5 = scenario("Postgres_FS").exec(chain5);
    ScenarioBuilder scenario6 = scenario("Mongo_FS").exec(chain6);
    ScenarioBuilder scenario7 = scenario("Postgres2_FS").exec(chain7);
    ScenarioBuilder scenario8 = scenario("Mongo2_FS").exec(chain8);

    //mvn gatling:test
    {
        setUp(
                scenario.injectOpen(rampUsers(10).during(10)),
                scenario2.injectOpen(rampUsers(10).during(10)),
                scenario3.injectOpen(rampUsers(10).during(10)),
                scenario4.injectOpen(rampUsers(10).during(10)),
                scenario5.injectOpen(rampUsers(10).during(10)),
                scenario6.injectOpen(rampUsers(10).during(10)),
                scenario7.injectOpen(rampUsers(10).during(10)),
                scenario8.injectOpen(rampUsers(10).during(10))

        ).protocols(httpProtocol);
    }

}
