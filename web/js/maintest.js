function resetLocalStorage()
{
    localStorage.setItem('obj', []);
    localStorage.setItem('cartCount', 0);
    localStorage.setItem('cartTotal', 0);
}

$('#selectphiship').on('change', function (f) {


    var options = this.getElementsByTagName('option');
    var optionHTML = options[this.selectedIndex].innerHTML;
    
    if(optionHTML.split('-')[1] == null){
        document.getElementById('phiship').innerHTML = "";
    }
    else{
        document.getElementById('phiship').innerHTML = optionHTML.split('-')[1]; 
        //dữ liệu dc lưu là tên + phí ship
        //tách chuỗi bằng dấu "-" và lấy phần tử thứ 2
    }

});
    
jQuery(document).ready(function ($) {
    
//        localStorage.setItem('obj', []);
//        localStorage.setItem('cartCount', 0);
//        localStorage.setItem('cartTotal', 0);
    var cartWrapper = $('.header-cart');
    var giohang = $('.giohang');
    var tableTotal = $('.shopping-cart-table.table');

    if (localStorage.getItem('obj') !== "") {
        var obj = JSON.parse(localStorage.getItem('obj'));
    } else {
        var obj = [];
    }
  
    if (cartWrapper.length > 0) {
     
        var cartBody = cartWrapper.find('.shopping-cart-list');
        var cartList = cartBody.find('ul').eq(0);
        var addToCartBtn = $('.add-to-cart');
        var cartTotal = cartWrapper.find('.total').find('span');
        var cartTrigger1 = cartWrapper.children('.dropdown-toggle');
        var cartTrigger = cartTrigger1.children('.header-btns-icon');
        var cartCount = cartTrigger.children('.qty');
        var undo = cartWrapper.find('.undo');
        var undoTimeoutId;

        cartCount.find('li').text(localStorage.getItem('cartCount'));
        cartTotal.text(localStorage.getItem('cartTotal'));
        tableTotal.find('.total').find('span').text(localStorage.getItem('cartTotal'));

  
        if (localStorage.getItem('obj') !== "") {

            JSON.parse(localStorage.getItem('obj')).forEach(function (f) {

                if (f.qty == 0) {
                    return;
                }
                //giỏ hàng dropdown
                var productAdded = $('<li class="product product-widget"><div class="product-thumb"><img src="./img/thumb-product01.jpg" alt=""></div><div class="product-body"><h3 class="product-price">' + f.price + ' x<span class="quantity" id="qty-' + f.id + '">' + f.qty + '</span></h3><h2 class="product-name"><a href="#">' + f.name + '</a></h2></div><button class="cancel-btn" data-id="' + f.id + '" data-price="' + f.price + '"><i class="fa fa-trash"></i></button></li>');
                cartList.prepend(productAdded);

                //giỏ hàng chi tiết
                var cartDetails = $('<tr><td class="thumb"><img src="./img/thumb-product01.jpg" alt=""></td><td class="details"><a href="#">' + f.name + '</a><ul><li><span>Size: XL</span></li></ul></td><td class="price text-center"><strong>' + f.price + '</strong></td><td class="qty text-center"><input class="input" id="input-' + f.id + '" data-id="' + f.id + '" type="number" value=' + f.qty + '></td><td class="total text-center"><strong class="primary-color">' + f.price * f.qty + '</strong></td><td class="text-right"><a class="main-btn icon-btn delete-cart-details" data-id="' + f.id + '"><i class="fa fa-close"></i></a></td></tr>');
                giohang.prepend(cartDetails);

            });
        }
  
  
        //add product to cart
        addToCartBtn.on('click', function (event) {
            event.preventDefault();
            addToCart($(this));
        });

        //open/close cart
        cartTrigger.on('click', function (event) {
            event.preventDefault();
            toggleCart();
        });

        //close cart when clicking on the .cd-cart-container::before (bg layer)
        cartWrapper.on('click', function (event) {
            if ($(event.target).is($(this)))
                toggleCart(true);
        });




        //delete an item from the cart
        cartList.on('click', '.cancel-btn', function (event) {
          
            event.preventDefault();
            removeProduct($(event.target).parents('.product.product-widget'), $(this));
        });

        function removeProduct(product, trigger) {
            clearInterval(undoTimeoutId);
            cartList.find('.deleted').remove();

            var topPosition = product.offset().top - cartBody.children('ul').offset().top,
                    productQuantity = Number(document.getElementById('qty-' + trigger.data('id')).innerHTML),
                    productTotPrice = Number(trigger.data('price')) * productQuantity;
            //alert(productQuantity);
            product.css('top', topPosition + 'px').addClass('deleted');

            //update items count + total price
            updateCartTotal(productTotPrice, false);
            updateCartCount(true, -productQuantity);
            cartList.find('.deleted').remove();

            obj.forEach(function (f, index) {
                if (f.id === String(trigger.data('id'))) {
                    obj.splice(index, 1);
                }
            });
            localStorage.setItem('obj', JSON.stringify(obj));
     
            window.location.reload();

//        undo.addClass('visible');
//        //wait 8sec before completely remove the item
//        undoTimeoutId = setTimeout(function () {
//            undo.removeClass('visible');
//            cartList.find('.deleted').remove();
//        }, 0);
        }
        
        giohang.on('click','.delete-cart-details', function (event) {
           
            event.preventDefault();
            removeCartDetails($(this));
            
        });

        function removeCartDetails(trigger) {

            obj.forEach(function (f, index) {

                if (f.id === String(trigger.data('id'))) {
               
                    obj.splice(index, 1);
                    updateCartTotal(f.price * f.qty, false);
                    updateCartCount(true, -f.qty);
                }
            });
            localStorage.setItem('obj', JSON.stringify(obj));
            //updateFromLocalStorage();
            window.location.reload();
        }


        giohang.on('change', '.input', function (event) {
            quickUpdateCartDetails($(this));

        });

        function quickUpdateCartDetails(trigger) {
            //alert(trigger.data('id'));
            obj.forEach(function (f) {
               
                if (f.id === String(trigger.data('id'))) {
                  // alert(f.id);
                   //alert(f.qty);
                   //alert(Number(document.getElementById('input-68').value));
                   
                    var inputQty=Number(document.getElementById('input-'+f.id).value);
                    if(f.qty < inputQty)
                    {
                        //alert(f.qty);
                       // alert(inputQty);
                        updateCartCount(true, inputQty - Number(f.qty));
                        updateCartTotal(Number(f.price) * (inputQty -Number(f.qty)), true);
                        f.qty = String(inputQty);
                    } else {
                        //alert('else');
                        updateCartCount(true, -(Number(f.qty)-inputQty));
                        updateCartTotal(Number(f.price) * (Number(f.qty)-inputQty), false);
                        f.qty = String(inputQty);
                    }
                  
//                    alert('abc');
//                    updateCartCount(true, -f.qty);
//                    updateCartTotal(f.price * f.qty, false);
//                    
//                    f.qty = Number(giohang.find('.input').val());
//                    updateCartCount(true, f.qty);
//
//                    updateCartTotal(f.price * f.qty, true);

                }
            });
            //alert('upd');
             localStorage.setItem('obj', JSON.stringify(obj));
                    window.location.reload();

//            cartTotal.text(price.toFixed(2));
//            cartCount.find('li').eq(0).text(quantity);
//            cartCount.find('li').eq(1).text(quantity + 1);
        }

        //update item quantity
        cartList.on('change', 'select', function (event) {
            quickUpdateCart();
        });


        //reinsert item deleted from the cart
        undo.on('click', 'a', function (event) {
            clearInterval(undoTimeoutId);
            event.preventDefault();
            cartList.find('.deleted').addClass('undo-deleted').one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function () {
                $(this).off('webkitAnimationEnd oanimationend msAnimationEnd animationend').removeClass('deleted undo-deleted').removeAttr('style');
                quickUpdateCart();
            });
            undo.removeClass('visible');
        });
    }

    function toggleCart(bool) {
        var cartIsOpen = (typeof bool === 'undefined') ? cartWrapper.hasClass('cart-open') : bool;

        if (cartIsOpen) {
            cartWrapper.removeClass('cart-open');
            //reset undo
            clearInterval(undoTimeoutId);
            undo.removeClass('visible');
            cartList.find('.deleted').remove();

            setTimeout(function () {
                cartBody.scrollTop(0);
                //check if cart empty to hide it
                if (Number(cartCount.find('li').eq(0).text()) === 0)
                    cartWrapper.addClass('empty');
            }, 500);
        } else {
            cartWrapper.addClass('cart-open');
        }
    }

    function addToCart(trigger) {

        var cartIsEmpty = cartWrapper.hasClass('empty');
        //update cart product list
        addProduct(trigger.data('price'), trigger.data('name'), trigger.data('id'),trigger.data('linkanh'));
        //update number of items 
        updateCartCount(cartIsEmpty);
        //update total price
        updateCartTotal(trigger.data('price'), true);
        //show cart
        cartWrapper.removeClass('empty');
    }

    function addProduct(price, name, id, linkanh) {
        //this is just a product placeholder
        //you should insert an item with the selected product info
        //replace productId, productName, price and url with your real product info

        if (document.getElementById('qty-' + id) === null) {

            var productAdded = $('<li class="product product-widget"><div class="product-thumb"><img src="./img/thumb-product01.jpg" alt=""></div><div class="product-body"><h3 class="product-price">' + price + ' x<span class="quantity" id="qty-' + id + '">0</span></h3><h2 class="product-name"><a href="#">' + name + '</a></h2></div><button class="cancel-btn" data-id="' + id + '" data-price="' + price + '"><i class="fa fa-trash"></i></button></li>');
            cartList.prepend(productAdded);

            //set localStorage
            obj.push({
                id: String(id),
                price: price,
                qty: String(Number(document.getElementById('qty-' + id).innerHTML) + 1),
                name: name
               });
            localStorage.setItem('obj', JSON.stringify(obj));

        } else {

            //var cartDet = JSON.parse(localStorage.getItem('obj'));
           
            //khi cuốn sách đc add hơn 1 lần
            obj.forEach(function (f) {
                if (f.id === String(id)) {                  
                    f.qty = String(Number(f.qty) + 1);
                     //alert(f.qty);
                }
            });
            localStorage.setItem('obj', JSON.stringify(obj));
        }

        document.getElementById('qty-' + id).innerHTML = String(Number(document.getElementById('qty-' + id).innerHTML) + 1);

        //var productAdded = $('<li class="product product-widget"><div class="product-thumb"><img src="./img/thumb-product01.jpg" alt=""></div><div class="product-body"><h3 class="quantity"><label for="cd-product-'+ id +'">Qty</label><span class="select"><select id="cd-product-52" name="quantity"><option value="1">1</option><option value="2">2</option><option value=ct-'+ id +'"3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option></select></span><span class="product-price">'+ price+'</span></h3><h2 class="product-name"><a href="#">'+name+'</a></h2></div><button class="cancel-btn"><i class="fa fa-trash"></i></button></li>');

    }

   

    function quickUpdateCart() {
        var quantity = 0;
        var price = 0;

        cartList.children('li:not(.deleted)').each(function () {
            var singleQuantity = Number($(this).find('select').val());
            quantity = quantity + singleQuantity;
            price = price + singleQuantity * Number($(this).find('.price').text().replace('$', ''));
        });

        cartTotal.text(price.toFixed(2));
        cartCount.find('li').eq(0).text(quantity);
        cartCount.find('li').eq(1).text(quantity + 1);
    }

    function updateCartCount(emptyCart, quantity) {
        if (typeof quantity === 'undefined') {
            var actual = Number(cartCount.find('li').eq(0).text()) + 1;
            var next = actual + 1;

            if (emptyCart) {
                cartCount.find('li').eq(0).text(actual);
                cartCount.find('li').eq(1).text(next);
            } else {
                cartCount.addClass('update-count');

                setTimeout(function () {
                    cartCount.find('li').eq(0).text(actual);
                }, 150);

                setTimeout(function () {
                    cartCount.removeClass('update-count');
                }, 200);

                setTimeout(function () {
                    cartCount.find('li').eq(1).text(next);
                }, 230);
            }
        } else {
            var actual = Number(cartCount.find('li').eq(0).text()) + quantity;
            var next = actual + 1;

            cartCount.find('li').eq(0).text(actual);
            cartCount.find('li').eq(1).text(next);
        }
        localStorage.setItem('cartCount', actual);
    }

    function updateCartTotal(price, bool) {

        bool ? cartTotal.text((Number(cartTotal.text()) + Number(price)).toFixed(0)) : cartTotal.text((Number(cartTotal.text()) - Number(price)).toFixed(2));

        localStorage.setItem('cartTotal', cartTotal.text());

    }
    
//    function updateFromLocalStorage() {
//
//        //JSON: dạng object
//        //stringify: dạng chuỗi để lưu vào localStorage
//        
//    }
});