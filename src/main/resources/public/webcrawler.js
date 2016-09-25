/**
 * Created by struan on 24/09/16.
 */
var viewModel = {
    initialUrl: ko.observable(),
    crawlMap: ko.observable(),
    queryRunning: ko.observable(false),
    isError: ko.observable(false),
    doCrawl: () => {
        if (viewModel.queryRunning() || viewModel.initialUrl().length === 0) {
            return;
        }
        viewModel.isError(false);
        viewModel.crawlMap(null);
        viewModel.queryRunning(true);
        if (viewModel.initialUrl().indexOf('http')) {
            viewModel.initialUrl('http://' + viewModel.initialUrl());
        }
        $.ajax({
            url: '/webcrawl?url=' + encodeURIComponent(viewModel.initialUrl())
        }).done((crawlMap) => {
            viewModel.crawlMap(crawlMap);
        }).fail(() => {
            viewModel.isError(true);
        }).always(() => {
            viewModel.queryRunning(false);
        });
    }
}

$().ready(() => {
    ko.applyBindings(viewModel, document.body);
});