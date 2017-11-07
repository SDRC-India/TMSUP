/**
 * ******************************************pie chart
 * *************************************************
 */
// ====================== doughnut chart=========================//
// ====================== doughnut chart=========================//
app
		.directive(
				"sdrcDoughnutChart",
				function($window) {
					function link(scope, el) {
						var el = el[0];
						scope
								.$watch(
										"dataprovider",
										function(data) {
											d3.select(el).selectAll("*")
													.remove();
											var allValueNotZero = false;
											for (var i = 0; i < data.length; i++) {
												if (data[i].value != 0) {
													allValueNotZero = true;
												}
											}
											if (!allValueNotZero) {
												var w = angular
														.element($window);
												var wnw = (w.width() < 800) ? w
														.width()
														: w.width() * 40 / 100;
												width = (w.width() > 800) ? wnw
														/ 1.3 + margin.right
														: (w.width() < 440) ? wnw - 30
																: wnw / 2 - 20;
												var svg = d3
														.select(el)
														.append("svg")
														.attr("id", "chart")
														.attr("class",
																"pieChartNoData")
														.attr("width",
																width - 20)
														.attr("height", 200)
														// .style("float",
														// "left")
														// .append("text")
														// .attr("transform",
														// "translate(" + (0) +
														// "," + (100) +
														// ")").text("No Data
														// Available").style({
														// "font-size": "25px",
														// "fill": "red"
														// })
														// d3.selectAll(".pieChartNoData")
														.append("text")
														.attr(
																"transform",
																"translate("
																		+ (20)
																		+ ","
																		+ (180)
																		+ ")")
														.html(
																"("
																		+ data[0].name
																		+ ")")
														.style(
																{
																	"fill" : "rgb(72, 72, 72)",
																	"font-size" : "14px"
																});

											}
											if (typeof data != 'undefined'
													&& data.length > 0
													&& allValueNotZero) {
												function draw(data) {
													var w = angular
															.element($window);
													var wnw = (w.width() < 800) ? w
															.width()
															: w.width() * 40 / 100;
													if (w.width() < 800)
														var wnh = 700;
													else
														wnh = 700;
													margin = {
														top : 20,
														right : 0,
														bottom : 20,
														left : 10
													};
															width = (w.width() > 800) ? wnw
																	/ 1.6
																	+ margin.right
																	: (w
																			.width() < 440) ? wnw - 30
																			: wnw / 2 - 20,
															height = (w.width() > 800) ? wnh / 3.75
																	: wnh / 3,
															radius = (w.width() < 900 && w
																	.width() > 800) ? Math
																	.min(width,
																			height) / 2.5
																	: Math
																			.min(
																					width,
																					height) / 2;

													var color = d3.scale
															.ordinal()
															.range(
																	[
																			"#f07258",
																			"#aedc5c" ]);

													// var color =
													// d3.scale.ordinal()
													// .range(["#00673c",
													// "#6E7035", "#69823F",
													// "#448343", "#499272"]);
													var arc = d3.svg
															.arc()
															.outerRadius(
																	radius - 10)
															.innerRadius(
																	radius / 3);

													var pie = d3.layout.pie()
															.value(function(d) {
																return d.value;
															}).sort(null);

													if (w.width() > 800) {
														var svg = d3
																.select(el)
																.append("svg")
																.attr("id",
																		"chart")
																.attr("width",
																		250)
																.attr(
																		"height",
																		height + 30)
																.append("g")
																.attr(
																		"transform",
																		"translate("
																				+ width
																				/ 3
																				+ ","
																				+ height
																				/ 2
																				+ ")");

														/*
														 * var legend =
														 * svg.selectAll('g')
														 * .attr("class",
														 * "legend")
														 * .attr("x",width/2)
														 * .attr("y",height/4)
														 * .data(pie(data))
														 * .enter().append("g")
														 * .attr("transform",
														 * function(d, i) {
														 * return
														 * "translate(40," + i *
														 * 20 + ")"; });
														 * 
														 * ////===========Legend
														 * for pie
														 * chart======================//////
														 * 
														 * 
														 * legend.append("rect")
														 * .attr("width",
														 * width/33)
														 * .attr("height",
														 * width/33)
														 * .attr("x",width/3.7)
														 * .attr("y",-(height/3))
														 * .style("fill",
														 * function(d) { return
														 * color(d.data.label);
														 * });
														 * 
														 * 
														 * legend.append("text")
														 * .attr("x", width/3.3)
														 * .attr("y",
														 * -(height/3.3)+2)
														 * .attr("dy", ".35em")
														 * .text(function(d) {
														 * return d.data.label;
														 * });
														 */
													}

													else {
														var svg = d3
																.select(el)
																.append("svg")
																.attr("id",
																		"chart")
																.attr("width",
																		300)
																.attr(
																		"height",
																		height + 30)
																.append("g")
																.attr(
																		"transform",
																		"translate("
																				+ (width / 2)
																				+ ","
																				+ (height / 2)
																				+ ")");

														/*
														 * var legend =
														 * svg.selectAll('g')
														 * .attr("class",
														 * "legend")
														 * .attr("x",width/2)
														 * .attr("y",height/4)
														 * .data(pie(data))
														 * .enter().append("g")
														 * .attr("transform",
														 * function(d, i) {
														 * return "translate("+
														 * (-(width / 2)) + "," +
														 * (-100 + i * 20) +
														 * ")"; });
														 * 
														 * ////===========Legend
														 * for pie
														 * chart======================//////
														 * 
														 * 
														 * legend.append("rect")
														 * .attr("width",
														 * width/33)
														 * .attr("height",
														 * width/33)
														 * .attr("x",width/3.7)
														 * .attr("y",-(height/3))
														 * .style("fill",
														 * function(d) { return
														 * color(d.data.label);
														 * });
														 * 
														 * 
														 * legend.append("text")
														 * .attr("x", width/3.3)
														 * .attr("y",
														 * -(height/3) + 7)
														 * .attr("dy", ".35em")
														 * .text(function(d) {
														 * return d.data.label;
														 * });
														 */
													}

													// ////===========End of
													// Legend for doughnut
													// chart======================//////

													function pieChart(data) {
														var g = svg.selectAll(
																".arc").data(
																pie(data))
																.enter()
																.append("g")
																.attr("class",
																		"arc")
																.attr("align",
																		"left");

														g
																.append("path")
																.attr("d", arc)
																.style(
																		{
																			"fill" : "#FFF",
																			"cursor" : "pointer"
																		})

																.on(
																		"mouseover",
																		function showPopover(
																				d) {
																			$(
																					this)
																					.popover(
																							{
																								title : '',
																								placement : 'auto top',
																								container : 'body',
																								trigger : 'manual',
																								html : true,
																								content : function() {
																									return "<span style='color:#2f4f4f'>"
																											+ d.data.label
																											+ " : </span>"
																											+ "<span style='color:#2f4f4f'>"
																											+ (d.endAngle
																													- d.startAngle > 0.0 ? Math
																													.round(Math
																															.round(1000 * (d.endAngle - d.startAngle))
																															/ (Math.PI * 2))
																													/ 10
																													+ '%'
																													: '')
																											+ "</span>";

																								}
																							});
																			$(
																					this)
																					.popover(
																							'show');
																			// $('.popover.fade.top.in').css('top',
																			// parseFloat($('.popover.fade.top.in').css('top').slice(0,
																			// -2))+$(window).scrollTop());
																		})
																.on(
																		"mouseout",
																		function removePopovers() {
																			$(
																					'.popover')
																					.each(
																							function() {
																								$(
																										this)
																										.remove();
																							});
																		})
																.transition()
																.delay(
																		function(
																				d,
																				i) {
																			return i * 500;
																		})
																.duration(500)
																.attrTween(
																		'd',
																		function(
																				d) {

																			var i = d3
																					.interpolate(
																							d.startAngle + 0.1,
																							d.endAngle);
																			return function(
																					t) {
																				d.endAngle = i(t);
																				return arc(d);
																			};
																		})
																.style(
																		"fill",
																		function(
																				d) {
																			return color(d.data.label);
																		});

														// ======================
														// title(below) for
														// doughnut
														// chart=========================//
														if (w.width() > 800) {
															g
																	.append(
																			"text")
																	.attr("x",
																			0)
																	.attr(
																			"y",
																			height / 1.7)
																	.attr(
																			"height",
																			20)
																	.style(
																			"text-anchor",
																			"Start")
																	.style(
																			"fill",
																			"#484848")
																	.style(
																			"z-index",
																			100)
																	.style(
																			{
																				"text-anchor" : "middle",
																				"font-size" : "14px"
																			})
																	.text(
																			function(
																					d) {
																				if (d.data.name == "Gender wise percent distribution of admitted Students")
																					return "(Enrolled)";
																				if (d.data.name == "Gender wise percent distribution of leftout Students")
																					return "(Left Out)";
																				if (d.data.name == "Social Categories wise percent distribution of admitted Students")
																					return "(Enrolled)";
																				if (d.data.name == "Social Categories wise percent distribution of left out Students")
																					return "(Left Out)";
																				else
																					return "("
																							+ d.data.name
																							+ ")";
																			});
														} else {
															g
																	.append(
																			"text")
																	.attr(
																			"x",
																			width / 30)
																	.attr(
																			"y",
																			height / 1.7)
																	.attr(
																			"height",
																			20)
																	.style(
																			"fill",
																			"#484848")
																	.style(
																			"z-index",
																			100)
																	.style(
																			{
																				"text-anchor" : "middle",
																				"font-size" : "14px"
																			})
																	.text(
																			function(
																					d) {
																				if (d.data.name == "Gender wise percent distribution of admitted Students")
																					return "(Enrolled)";
																				if (d.data.name == "Gender wise percent distribution of leftout Students")
																					return "(Left Out)";
																				if (d.data.name == "Social Categories wise percent distribution of admitted Students")
																					return "(Enrolled)";
																				if (d.data.name == "Social Categories wise percent distribution of left out Students")
																					return "(Left Out)";
																				else
																					return "("
																							+ d.data.name
																							+ ")";
																			});
														}

														// =====================percent
														// value over the
														// doughnut
														// chart=========================//
														g
																.append("text")
																.attr(
																		"transform",
																		function(
																				d) {
																			return "translate("
																					+ arc
																							.centroid(d)
																					+ ")";
																		})
																.attr("dy",
																		".35em")
																.attr("class",
																		"percentVal")
																.style(
																		{
																			"text-anchor" : "middle",
																			"fill" : "#000"
																		})
																.text(
																		function getPercent(
																				d) {
																			return (d.endAngle
																					- d.startAngle > 0.0 ? Math
																					.round(Math
																							.round(1000 * (d.endAngle - d.startAngle))
																							/ (Math.PI * 2)
																							/ 10)
																					+ '%'
																					: '');
																		});
													}
													;
													$(".percentVal")
															.delay(1500)
															.fadeIn();

													pieChart(data);
												}

												draw(data);

											}
											;

										});

					}
					return {
						restrict : "E",
						scope : {
							dataprovider : "="
						},
						link : link
					};

				});

app
		.directive(
				"pieChart",
				function($window) {
					function link(scope, el) {
						var el = el[0];
						scope
								.$watch(
										"dataprovider",
										function(data) {
											d3.select(el).selectAll("*")
													.remove();
											/*
											 * for(var i=0; i < data.length;
											 * i++){ if(data[i].value != 0){
											 * allValueNotZero = true; } }
											 */
											var margin = {
												right : 5,
												top : 5,
												left : 5,
												bottom : 5
											}
											/*
											 * if(data){ var w =
											 * angular.element($window); var wnw
											 * =(w.width()< 800)?
											 * w.width():w.width() * 40 / 100;
											 * width = (w.width()> 800) ?
											 * wnw/1.3 + margin.right :
											 * (w.width()< 440) ? wnw-30 :
											 * wnw/2-20; var svg =
											 * d3.select(el).append("svg").attr("id","chart").attr("class",
											 * "pieChartNoData") .attr("width",
											 * width-20) .attr("height", 200) //
											 * .style("float", "left") //
											 * .append("text") //
											 * .attr("transform", "translate(" +
											 * (0) + "," + (100) + ")").text("No
											 * Data Available").style({ //
											 * "font-size": "25px", "fill":
											 * "red" // }) //
											 * d3.selectAll(".pieChartNoData")
											 * .append("text")
											 * .attr("transform", "translate(" +
											 * (20) + "," + (180) +
											 * ")").html("(" + data[0].name +
											 * ")") .style({"fill": "rgb(72, 72,
											 * 72)", "font-size": "14px"}); }
											 */
											if (typeof data != 'undefined'
													&& data.length > 0) {
												function draw(data) {
													var w = angular
															.element($window);
													var wnw = (w.width() < 800) ? w
															.width()
															: w.width() * 40 / 100;
													if (w.width() < 800)
														var wnh = 700;
													else
														wnh = 700;
													margin = {
														top : 20,
														right : 0,
														bottom : 20,
														left : 10
													};
															width = (w.width() > 800) ? wnw
																	/ 1.3
																	+ margin.right
																	: (w
																			.width() < 440) ? wnw - 50
																			: wnw / 2 - 50,
															height = (w.width() > 800) ? wnh / 3 - 20
																	: wnh / 3 - 30,
															radius = (w.width() < 900 && w
																	.width() > 800) ? Math
																	.min(width,
																			height) / 2.5 - 30
																	: Math
																			.min(
																					width,
																					height) / 2 - 30;

													var color = d3.scale
															.ordinal()
															.range(
																	[
																			"#edb761",
																			"#28323c",
																			"#386d5d",
																			"#2d4f6c" ]);
													var chartColor = {
														"Enrolled" : "#aedc5c",
														"Left Out" : "#f07258"
													}
													var arc = d3.svg
															.arc()
															.outerRadius(
																	radius - 10)
															.innerRadius(0);

													var pie = d3.layout
															.pie()
															.value(
																	function(d) {
																		return parseInt(d.value);
																	}).sort(
																	null);

													if (w.width() > 800) {
														var svg = d3
																.select(el)
																.append("svg")
																.attr("id",
																		"chart")
																.attr(
																		"width",
																		height - 30)
																.attr(
																		"height",
																		height - 30)
																.append("g")
																.attr(
																		"transform",
																		"translate("
																				+ (height / 2 - 15)
																				+ ","
																				+ (height / 2 - 15)
																				+ ")");
													}

													else {
														var svg = d3
																.select(el)
																.append("svg")
																.attr("id",
																		"chart")
																.attr(
																		"width",
																		width - 20)
																.attr(
																		"height",
																		height + 30)
																.append("g")
																.attr(
																		"transform",
																		"translate("
																				+ (width / 2 - 10)
																				+ ","
																				+ (height / 2)
																				+ ")");
													}

													// ////===========End of
													// Legend for doughnut
													// chart======================//////

													function pieChart(data) {
														var g = svg.selectAll(
																".arc").data(
																pie(data))
																.enter()
																.append("g")
																.attr("class",
																		"arc")
																.attr("align",
																		"left");

														g
																.append("path")
																.attr("d", arc)
																.style(
																		{
																			"fill" : "#FFF"
																		})

																.on(
																		"mouseover",
																		function showPopover(
																				d) {
																			$(
																					this)
																					.popover(
																							{
																								title : '',
																								placement : 'auto top',
																								container : 'body',
																								trigger : 'manual',
																								html : true,
																								content : function() {
																									return "<span style='color:#2f4f4f'>"
																											+ d.data.label
																											+ " : </span>"
																											+ "<span style='color:#2f4f4f'>"
																											+ (d.endAngle
																													- d.startAngle > 0.0 ? Math
																													.round(Math
																															.round(1000 * (d.endAngle - d.startAngle))
																															/ (Math.PI * 2))
																													/ 10
																													+ '%'
																													: '')
																											+ "</span>";

																								}
																							});
																			$(
																					this)
																					.popover(
																							'show');
																			// $('.popover.fade.top.in').css('top',
																			// parseFloat($('.popover.fade.top.in').css('top').slice(0,
																			// -2))+$(window).scrollTop());
																		})
																.on(
																		"mouseout",
																		function removePopovers() {
																			$(
																					'.popover')
																					.each(
																							function() {
																								$(
																										this)
																										.remove();
																							});
																		})
																.on("click",
																		click)
																.transition()
																.delay(
																		function(
																				d,
																				i) {
																			return i * 500;
																		})
																.duration(500)
																.attrTween(
																		'd',
																		function(
																				d) {

																			var i = d3
																					.interpolate(
																							d.startAngle + 0.1,
																							d.endAngle);
																			return function(
																					t) {
																				d.endAngle = i(t);
																				return arc(d);
																			};
																		})
																.attr(
																		"class",
																		function(
																				d) {
																			if (d.endAngle)
																				return d.data.label;
																			else
																				return d.data.label
																						+ " zeroValue";
																		})
																.style(
																		"fill",
																		function(
																				d) {
																			return chartColor[d.data.label];
																		});

														function click(d) {

															var scope = angular
																	.element(
																			"body")
																	.scope();

														}

														// ======================
														// title(below) for
														// doughnut
														// chart=========================//
														/*
														 * if(w.width()> 800){
														 * g.append("text")
														 * .attr("x",width/70)
														 * .attr("y",
														 * height/2.1)
														 * .attr("height",20)
														 * .style("text-anchor",
														 * "Start")
														 * .style("fill",
														 * "#484848").style("z-index",
														 * 100)
														 * .style({"text-anchor":
														 * "middle",
														 * "font-size": "14px"})
														 * .text(function(d) {
														 * return "(" + " "
														 * +d.data.name + " "
														 * +")" ; }); } else{
														 * g.append("text")
														 * .attr("x",width/30)
														 * .attr("y",
														 * height/3+15)
														 * .attr("height",20)
														 * .style("fill",
														 * "#484848").style("z-index",
														 * 100)
														 * .style({"text-anchor":
														 * "middle",
														 * "font-size": "14px"})
														 * .text(function(d) {
														 * return "(" + " "
														 * +d.data.name + " "
														 * +")" ; }); }
														 */

														// =====================percent
														// value over the
														// doughnut
														// chart=========================//
														g
																.append("text")
																.attr(
																		"transform",
																		function(
																				d) {
																			return "translate("
																					+ arc
																							.centroid(d)
																					+ ")";
																		})
																.attr("dy",
																		".35em")
																.attr("class",
																		"percentVal")
																.style(
																		{
																			"text-anchor" : "middle",
																			"fill" : "#000"
																		})
																.text(
																		function getPercent(
																				d) {
																			return (d.endAngle
																					- d.startAngle > 0.0 ? Math
																					.round(Math
																							.round(1000 * (d.endAngle - d.startAngle))
																							/ (Math.PI * 2)
																							/ 10)
																					+ '%'
																					: '');
																		});
													}
													;
													$(".percentVal")
															.delay(1500)
															.fadeIn();

													pieChart(data);
												}
												if (data)
													draw(data);

											}
											;

										});

					}
					return {
						restrict : "E",
						scope : {
							dataprovider : "="
						},
						link : link
					};

				});

app.directive("sdrcStackedBarChart", function($window) {
	function link(scope, el) {
		var el = el[0];
		scope.$watch("dataprovider", function(data) {

			d3.select(el).selectAll("*").remove();
			//			var data = [ {
			//				"interest_rate" : "< 4%",
			//				"Default" : 60,
			//				"Charge-off" : 20
			//
			//			}, {
			//				"interest_rate" : "4-7.99%",
			//				"Charge-off" : 2,
			//				"Default" : 30
			//
			//			} ];

			var w = angular
			.element($window);
			
			var left=400,right=20
			if(w.width() < 440)
				{
				left=40
				right=10
				}
				else if(w.width() < 800)
		{
					left=80
					right=20
		
		}
			var margin = {
				top : 20,
				right : right,
				bottom : 40,
				left : left
			}, width = 450 - margin.left - margin.right, height = 315
					- margin.top - margin.bottom, that = this;
			
			var wnw = (w.width() < 800) ? w
					.width()
					: w.width() * 40 / 100;
			width = (w.width() > 800) ? wnw
					/ 1.3 + margin.right
					: (w.width() < 440) ? wnw - 80
							: wnw / 2 - 20;
			var barWidth=.4;
			if(w.width() < 440)
				barWidth=.6
			var x = d3.scale.ordinal().rangeRoundBands([ 0, width ],barWidth );

			var y = d3.scale.linear().rangeRound([ height, 0 ]);

			//			var color = d3.scale.category20();
			var color = d3.scale.ordinal().range(["#aedc5c","#f07258" ]);
			var xAxis = d3.svg.axis().scale(x).orient("bottom");
			
			

			var yAxis = d3.svg.axis().scale(y).orient("left").tickFormat(
					d3.format(".0%"));

			var svg = d3.select(el).append("svg").attr("width",
					width + margin.left + margin.right).attr("height",
					height + margin.top + margin.bottom).append("g").attr(
					"transform",
					"translate(" + margin.left + "," + margin.top + ")");

			var labels=[{
				enrolled:0,
				leftout:0
			}];
			color.domain(d3.keys(labels[0]).filter(function(key) {
				return key !== "label";
			}));

			data.forEach(function(d) {
				var y0 = 0;

				d.rates = color.domain().map(function(name) {
				//	console.log();
					;
					return {
						name : name,
						y0 : y0,
						y1 : y0 += +d[name],
						amount : d[name]
					};
				});
				d.rates.forEach(function(d) {
					d.y0 /= y0;
					d.y1 /= y0;
				});

				//console.log(data);
			});

//			data.sort(function(a, b) {
//				return b.rates[0].y1 - a.rates[0].y1;
//			});
//			data.sort(function (a, b) {
//				return b.rates[0].y1 - a.rates[0].y1;
//			});
			x.domain(data.map(function(d) {
				return d.label;
			}));

			svg.append("g").attr("class", "x axis").attr("transform",
					"translate(0," + height + ")").call(xAxis);

			svg.append("g").attr("class", "y axis").call(yAxis);

			var interest_rate = svg.selectAll(".label").data(data)
					.enter().append("g").attr("class", "label").attr(
							"transform",
							function(d) {
								return "translate(" + x(d.label)
										+ ",0)";
							});

			interest_rate.selectAll("rect").data(function(d) {
				return d.rates;
			}).enter().append("rect").attr("width", x.rangeBand()).attr("y",
					function(d) {
						return y(d.y1);
					}).attr("height", function(d) {
				return y(d.y0) - y(d.y1);
			}).style("fill", function(d) {
				return color(d.name);
			}).on(
					'mouseover',
					function(d) {
						showPopover.call(this, d);
						}).on('mouseout', function() {
							removePopovers();
			});

			
			function showPopover(d) {
				$(this).popover(
						{
							title : '',
							placement : 'auto top',
							container : 'body',
							trigger : 'manual',
							html : true,
							content : function() {
								return capitalize(d.name)
										+ " : "
										+ d.amount;
							}
						});
				$(this).popover('show');
			}
			
			function removePopovers() {
				$('.popover').each(function() {
					$(this).remove();
				});
			}
			function capitalize(s)
			{
			    return s[0].toUpperCase() + s.slice(1);
			}
			
			var legend = svg.selectAll(".legend").data(
					color.domain().slice().reverse()).enter().append("g").attr(
					"class", "legend").attr("transform", function(d, i) {
				return "translate(" + i * -90 + ",283)";
			});

			legend.append("rect").attr("x", width + -53).attr("width", 10)
					.attr("height", 10).style("fill", color);

			legend.append("text").attr("x", width - 40).attr("y", 5).attr(
					"width", 40).attr("dy", ".35em").style("text-anchor",
					"start").text(function(d) {
				return capitalize(d);
			});
		}

		);

	}
	return {
		restrict : "E",
		scope : {
			dataprovider : "="
		},
		link : link
	};

});

myAppConstructor.directive('onlyElevenDigits', function() {

	return {
		restrict : 'A',
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) {
				return;
			}

			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					var val = '';
				}

				var clean = val.replace(/[^0-9]/g, '');
				if (!angular.isUndefined(clean)) {
					var num = 0;
					if (clean.length > 10) {
						num = clean.slice(0, 11);
						clean = num;
					}

				}

				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});

			element.bind('keypress', function(event) {
				if (typeof InstallTrigger !== 'undefined') {
					if (event.charCode === 101 || event.charCode === 46) {
						event.preventDefault();
					}
				} else {
					if (event.keyCode === 101 || event.keyCode === 46) {
						event.preventDefault();
					}
				}

			});
		}
	};
});
myAppConstructor.directive('fiftyCharactersValidation', function() {

	return {
		restrict : 'A',
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) {
				return;
			}

			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					var val = '';
				}

				var clean = val;
				if (!angular.isUndefined(clean)) {
					var num = 0;
					if (clean.length > 50) {
						num = clean.slice(0, 50);
						clean = num;
					}
					clean = clean.replace(/[^a-zA-z ,.]/g, '');
					clean = clean.replace('^', '');
					clean = clean.replace(/\\/g, '');
					clean = clean.replace('[', '');
					clean = clean.replace(']', '');

				}

				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
		}
	};
});
myAppConstructor.directive('twentyAlphaNumericValidation', function() {

	return {
		restrict : 'A',
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) {
				return;
			}

			ngModelCtrl.$parsers.push(function(val) {
				if (angular.isUndefined(val)) {
					var val = '';
				}

				var clean = val;
				if (!angular.isUndefined(clean)) {
					var num = 0;
					if (clean.length > 20) {
						num = clean.slice(0, 20);
						clean = num;
					}
					clean = clean.replace(/[^a-zA-z0-9]/g, '');
					clean = clean.replace('^', '');
					clean = clean.replace(/\\/g, '');
					clean = clean.replace('[', '');
					clean = clean.replace(']', '');
					clean = clean.replace('_', '');
				}

				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});
			element.bind('keypress', function(event) {
				if (typeof InstallTrigger !== 'undefined') {
					if (event.charCode === 32) {
						event.preventDefault();
					}
				} else {
					if (event.keyCode === 32) {
						event.preventDefault();
					}
				}

			});
		}
	};
});

var rowWidthList = [];

$(".table-header-fixed").scroll(function() {
	if ($(this).scrollTop() > 40) {
		$(".static-header thead").css({
			'visibility' : 'visible'
		});
		$(".filter-section-container").css({
			"position" : "relative"
		});
		$(".filter-main").css("margin-top", "30px")
		$(".download-container").css({
			"position" : "relative"
		});
		// $(".static-header thead").css({'display': 'block'});
	} else {
		$(".static-header thead").css({
			'visibility' : 'hidden'
		});
		$(".filter-section-container").css({
			"position" : "absolute",
			"right" : "0px"
		});
		// $(".static-header thead").css({'display': 'none'});
		$(".filter-main").css("margin-top", "66px")
		$(".download-container").css({
			"position" : "absolute"
		});
	}
	if ($(".table-header-fixed").scrollLeft() > 0) {
		$(".static-header").scrollLeft($(".table-header-fixed").scrollLeft());
	}
});
$(".table-header-link-fixed").scroll(function() {
	if ($(this).scrollTop() > 40) {
		if (angular.element("body").scope().showStudentTable)
			$(".static-header.static-student-header thead").css({
				'visibility' : 'visible'
			});
		else
			$(".static-header.static-school-header thead").css({
				'visibility' : 'visible'
			});
		$(".filter-section-container").css({
			"position" : "relative"
		});
		$(".filter-main").css("margin-top", "30px")
		$(".download-container").css({
			"position" : "relative"
		});
		// $(".static-header thead").css({'display': 'block'});
	} else {
		if (angular.element("body").scope().showStudentTable)
			$(".static-header.static-student-header thead").css({
				'visibility' : 'hidden'
			});
		else
			$(".static-header.static-school-header thead").css({
				'visibility' : 'hidden'
			});
		$(".filter-section-container").css({
			"position" : "absolute",
			"right" : "0px"
		});
		// $(".static-header thead").css({'display': 'none'});
		$(".filter-main").css("margin-top", "66px")
		$(".download-container").css({
			"position" : "absolute"
		});
	}
	if ($(".table-header-fixed").scrollLeft() > 0) {
		$(".static-header").scrollLeft($(".table-header-fixed").scrollLeft());
	}
});
