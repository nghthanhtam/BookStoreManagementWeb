function resetLocalStorage()
{
    localStorage.setItem('obj', []);
    localStorage.setItem('cartCount', 0);
    localStorage.setItem('cartTotal', 0);
}

$('#selectphiship').on('change', function (f) {


    var options = this.getElementsByTagName('option'),
            optionHTML = options[this.selectedIndex].innerHTML;
    
    if(optionHTML.split('-')[1] == null){
        document.getElementById('phiship').innerHTML = "";
    }
    else{
        document.getElementById('phiship').innerHTML = optionHTML.split('-')[1]; 
        var phiShip = Number(document.getElementById('phiship').innerHTML);
     
        var tongTien = Number(localStorage.getItem('cartTotal')) + phiShip;
        document.getElementById('total2').innerHTML = String(tongTien);
        
        //dữ liệu dc lưu là tên + phí ship
        //tách chuỗi bằng dấu "-" và lấy phần tử thứ 2
    }

});
    
jQuery(document).ready(function ($) {
 
//    localStorage.setItem('obj', []);
//        localStorage.setItem('cartCount', 0);
//        localStorage.setItem('cartTotal', 0);
 

if (localStorage.getItem('obj') == 'undefined' || localStorage.getItem('obj') == null) {
    localStorage.setItem('obj', []);
    localStorage.setItem('cartCount', 0);
    localStorage.setItem('cartTotal', 0);
}
    
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
//        cartTotal.text(localStorage.getItem('cartTotal'));
//        tableTotal.find('.total').find('span').text(localStorage.getItem('cartTotal'));

        
        if (localStorage.getItem('obj') !== "") {
            
            $.ajax
            (
                {
                    url: '/ajax',
                    dataType: 'json',
                    data: JSON.stringify(obj),
                    contentType: "application/json; charset=utf-8",
                    type: 'post',
                    cache: false,
                    success: function (data) {
                         data.forEach(function (f){
                             
                            //giỏ hàng thu nhỏ
                            var productAdded = $('<li id="cart-'+ f.maSach +'" class="product product-widget"><div class="product-thumb"><img src="'+ f.anhDaiDien + '" alt=""></div><div class="product-body"><h3 class="product-price" id="price-' + f.maSach + '">' + f.giaBan + ' x<span class="quantity" id="qty-' + f.maSach + '">' + 1 + '</span></h3><h2 class="product-name"><a href="#">' + f.tenSach + '</a></h2></div><button class="cancel-btn" data-id="' + f.maSach + '" data-price="' + f.giaBan + '"><i class="fa fa-trash"></i></button></li>');
                            cartList.prepend(productAdded); 
                        
                            //giỏ hàng chi tiết
                            var cartDetails = $('<tr id="' + f.maSach + '"><td class="thumb"><img src='+ f.anhDaiDien + ' alt=""></td><td class="details"><a href="#">' + f.tenSach + '</a><ul><li><span>Size: XL</span></li></ul></td><td class="price text-center" id="price-' + f.maSach + '"><strong>' + f.giaBan + '</strong></td><td class="qty text-center"><input class="input" id="input-' + f.maSach + '" data-id="' + f.maSach + '" type="number" value=' + 1 + '></td><td class="total text-center"><strong class="primary-color" id="total-' + f.maSach + '">' + f.giaBan * 1 + '</strong></td><td class="text-right"><a class="main-btn icon-btn delete-cart-details" data-id="' + f.maSach + '"><i class="fa fa-close"></i></a></td></tr>');
                            giohang.prepend(cartDetails);
                          
                        });
                        
                        var tongTien = 0;
                        JSON.parse(localStorage.getItem('obj')).forEach(function (f) {
                            if (f.qty == 0) {
                                f.qty = 1;
                            }                          
                           document.getElementById('qty-' + f.id).innerHTML = String(f.qty);
                           if(document.getElementById('total-' + f.id)) document.getElementById('total-' + f.id).innerHTML *= f.qty;                     
                           if(document.getElementById('input-' + f.id)) document.getElementById('input-' + f.id).value = String(f.qty);
                           
                           var price = document.getElementById('price-' + f.id).innerHTML.split(' ')[0];  
                           tongTien += Number(price)*Number(f.qty);
                       
                        });
                        if(document.getElementById('total')) document.getElementById('total').innerHTML = String(tongTien);
                        if(document.getElementById('total2')) document.getElementById('total2').innerHTML = String(tongTien);
               
                        cartTotal.text(String(tongTien));
                    },
                    error: function () {
                        var json = $.parseJSON(data);
                        alert(json.error);
                    }
                }
            );
            
            
//            JSON.parse(localStorage.getItem('obj')).forEach(function (f) {
//              
//                if (f.qty == 0) {
//                    return;
//                }
//                //giỏ hàng dropdown
//                var productAdded = $('<li class="product product-widget"><div class="product-thumb"><img src="./img/thumb-product01.jpg" alt=""></div><div class="product-body"><h3 class="product-price">' + f.price + ' x<span class="quantity" id="qty-' + f.id + '">' + f.qty + '</span></h3><h2 class="product-name"><a href="#">' + f.name + '</a></h2></div><button class="cancel-btn" data-id="' + f.id + '" data-price="' + f.price + '"><i class="fa fa-trash"></i></button></li>');
//                cartList.prepend(productAdded);
//
//                //giỏ hàng chi tiết
//                var cartDetails = $('<tr><td class="thumb"><img src="./img/thumb-product01.jpg" alt=""></td><td class="details"><a href="#">' + f.name + '</a><ul><li><span>Size: XL</span></li></ul></td><td class="price text-center"><strong>' + f.price + '</strong></td><td class="qty text-center"><input class="input" id="input-' + f.id + '" data-id="' + f.id + '" type="number" value=' + f.qty + '></td><td class="total text-center"><strong class="primary-color">' + f.price * f.qty + '</strong></td><td class="text-right"><a class="main-btn icon-btn delete-cart-details" data-id="' + f.id + '"><i class="fa fa-close"></i></a></td></tr>');
//                giohang.prepend(cartDetails);
//
//            });
        }
  
  addToCartBtn.on('click', function (event) {
           var id = $(this).data("id");
            var listTemp=[];
            listTemp.push({id: String(id)});

    $.ajax
            (
                    {
                        url: '/ajax',
                        dataType: 'json',
                        data: JSON.stringify(listTemp),
                        contentType: "application/json; charset=utf-8",
                        type: 'post',
                        cache: false,
                        success: function (data) {
                       
                            //alert(data[0]);
                            //alert(data[0].tenSach);
                            
                            event.preventDefault();
                            addToCart($(this), data[0]);
   
                        },
                        error: function () {
                            var json = $.parseJSON(data);
                            alert(json.error);
                        }
                    }
            );

        });
        
        //add product to cart
//        addToCartBtn.on('click', function (event) {
//            event.preventDefault();
//            addToCart($(this), gId, gPrice);
//        });

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
     
            var element = document.getElementById(trigger.data('id'));
            element.parentNode.removeChild(element);
            //window.location.reload();

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
                    var price = Number(document.getElementById('price-' + f.id).innerHTML.split(' ')[0]);
                    
                    obj.splice(index, 1);
                    updateCartTotal(price * f.qty, false);
                    updateCartCount(true, -f.qty);
                }
            });
            localStorage.setItem('obj', JSON.stringify(obj));
  
            var cartelement = document.getElementById('cart-'+trigger.data('id'));
            cartelement.parentNode.removeChild(cartelement);
            
            var element = document.getElementById(trigger.data('id'));
            element.parentNode.removeChild(element);
            //window.location.reload();
        }


        giohang.on('change', '.input', function (event) {
            quickUpdateCartDetails($(this));

        });

        function quickUpdateCartDetails(trigger) {
            
            obj.forEach(function (f) {
               
                if (f.id === String(trigger.data('id'))) {
                  
                    var price = Number(document.getElementById('price-' + f.id).innerHTML.split(' ')[0]);
                    var inputQty = Number(document.getElementById('input-'+f.id).value);
                    if(inputQty == 0) inputQty =1;
                    
                    if(f.qty < inputQty)
                    {                    
                        updateCartCount(true, inputQty - Number(f.qty));
                        updateCartTotal(Number(price) * (inputQty -Number(f.qty)), true);
                        f.qty = String(inputQty);
                    } else {
                        //alert('else');
                        updateCartCount(true, -(Number(f.qty)-inputQty));
                        updateCartTotal(Number(price) * (Number(f.qty)-inputQty), false);
                        f.qty = String(inputQty);
                    }                

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

    function addToCart(trigger, data) {

        var cartIsEmpty = cartWrapper.hasClass('empty');
        //update cart product list
        addProduct(data.giaBan, data.tenSach, data.maSach, data.anhDaiDien);
        //update number of items 
        updateCartCount(cartIsEmpty);
        //update total price
        updateCartTotal(data.giaBan, true);
        //show cart
        cartWrapper.removeClass('empty');
    }

    function addProduct(price, name, id, linkanh) {
    
        if (document.getElementById('qty-' + id) === null) {
    
            var productAdded = $('<li class="product product-widget"><div class="product-thumb"><img src="'+linkanh+'" alt=""></div><div class="product-body"><h3 class="product-price">' + price + ' x<span class="quantity" id="qty-' + id + '">0</span></h3><h2 class="product-name"><a href="#">' + name + '</a></h2></div><button class="cancel-btn" data-id="' + id + '" data-price="' + price + '"><i class="fa fa-trash"></i></button></li>');
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

        bool ? cartTotal.text((Number(cartTotal.text()) + Number(price)).toFixed(0)) : cartTotal.text((Number(cartTotal.text()) - Number(price)).toFixed(0));
        
        localStorage.setItem('cartTotal', cartTotal.text());
        if(document.getElementById('total')) document.getElementById('total').innerHTML = cartTotal.text();
        if(document.getElementById('total2')) document.getElementById('total2').innerHTML = cartTotal.text();
        
    }
    
//    function updateFromLocalStorage() {
//
//        //JSON: dạng object
//        //stringify: dạng chuỗi để lưu vào localStorage
//        
//    }
});