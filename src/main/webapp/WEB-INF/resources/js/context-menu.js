(function ($, window) {

    $.fn.contextMenu = function (settings) {

        return this.each(function () {

            // Open context menu
            $(this).on("contextmenu", function (e) {
                $("#context-menu").remove();

                var tr = $(e.target).closest("tr");
                var identity = tr.data("identity");
                var actions = settings.actions;

                $("tr").removeClass("active");
                tr.addClass("active");

                $(document.body).append($("<ul>", {
                    id: "context-menu",
                    class: "dropdown-menu",
                    role: "menu"
                }));

                settings.menuSelector = $("#context-menu");

                for (var action in actions) {
                    var act = actions[action];
                    var li = $("<li>");
                    var a = $("<a>", {
                        class: "text-primary",
                        href: act.link + identity,
                        text: act.text
                    });
                    var span = $("<span>", {
                        class: "glyphicon glyphicon-" + act.icon,
                        style: "margin-right: 10px"
                    })
                    a.prepend(span);
                    li.append(a);
                    settings.menuSelector.append(li);
                }

                //open menu
                $(settings.menuSelector)
                    .show()
                    .css({
                        position: "absolute",
                        left: getLeftLocation(e),
                        top: getTopLocation(e)
                    })
                    .off('click')
                    .on('click', function (e) {
                        $(this).hide();

                        var $identity = $(this).data("identity");
                        var $action = $(e.target).data("action");

                        settings.menuSelected.call(this, $identity, $action);
                    });

                return false;
            });

            //make sure menu closes on any click
            $(document).click(function () {
                $("tr").removeClass("active");
                $("#context-menu").remove();
            });
        });

        function getLeftLocation(e) {
            var mouseWidth = e.pageX;
            var pageWidth = $(window).width();
            var menuWidth = $(settings.menuSelector).width();

            // opening menu would pass the side of the page
            if (mouseWidth + menuWidth > pageWidth &&
                menuWidth < mouseWidth) {
                return mouseWidth - menuWidth;
            }
            return mouseWidth;
        }

        function getTopLocation(e) {
            var mouseHeight = e.pageY;
            var pageHeight = $(window).height();
            var menuHeight = $(settings.menuSelector).height();

            // opening menu would pass the bottom of the page
            if (mouseHeight + menuHeight > pageHeight &&
                menuHeight < mouseHeight) {
                return mouseHeight - menuHeight;
            }
            return mouseHeight;
        }

    };
})(jQuery, window);