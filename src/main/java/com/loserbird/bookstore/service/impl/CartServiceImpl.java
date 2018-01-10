package com.loserbird.bookstore.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.loserbird.bookstore.common.Const;
import com.loserbird.bookstore.common.ResponseCode;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.dao.BookMapper;
import com.loserbird.bookstore.dao.CartMapper;
import com.loserbird.bookstore.pojo.Book;
import com.loserbird.bookstore.pojo.Cart;
import com.loserbird.bookstore.service.ICartService;
import com.loserbird.bookstore.util.BigDecimalUtil;
import com.loserbird.bookstore.vo.CartBookVo;
import com.loserbird.bookstore.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private BookMapper bookMapper;

    public ServerResponse<CartVo> add(Integer userId, Integer bookId, Integer count){
        if(bookId == null || count == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdBookId(userId,bookId);
        if(cart == null){
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setBookId(bookId);
            cartItem.setUserId(userId);
            cartMapper.insert(cartItem);
        }else{
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }

    public ServerResponse<CartVo> update(Integer userId,Integer bookId,Integer count){
        if(bookId == null || count == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdBookId(userId,bookId);
        if(cart != null){
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }

    public ServerResponse<CartVo> deleteBook(Integer userId,String bookIds){
        List<String> BookList = Splitter.on(",").splitToList(bookIds);
        if(CollectionUtils.isEmpty(BookList)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdbookIds(userId,BookList);
        return this.list(userId);
    }

    public ServerResponse<CartVo> delete(Integer userId,Integer bookId){
        if(bookId == null || bookId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdbookId(userId,bookId);
        return this.list(userId);
    }


    public ServerResponse<CartVo> list (Integer userId){
        CartVo cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }



    public ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer bookId,Integer checked){
        cartMapper.checkedOrUncheckedBook(userId,bookId,checked);
        return this.list(userId);
    }

    public ServerResponse<Integer> getCartBookCount(Integer userId){
        if(userId == null){
            return ServerResponse.createBySuccess(0);
        }
        return ServerResponse.createBySuccess(cartMapper.selectCartBookCount(userId));
    }

    @Override
    public int updateBatch(Integer userId, List<Integer> bookIds) {
        return cartMapper.updateBatch(userId,bookIds);
    }

    @Override
    public List<Cart> selectCheckedCartByUserId(Integer userId) {
        return cartMapper.selectCheckedCartByUserId(userId);
    }


    //构造购物车vo
    private CartVo getCartVoLimit(Integer userId){
        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartBookVo> CartBookVoList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");

        if(CollectionUtils.isNotEmpty(cartList)){
            for(Cart cartItem : cartList){
                CartBookVo CartBookVo = new CartBookVo();
                CartBookVo.setId(cartItem.getId());
                CartBookVo.setUserId(userId);
                CartBookVo.setBookId(cartItem.getBookId());

                Book Book = bookMapper.selectByPrimaryKey(cartItem.getBookId());
                if(Book != null){
                    CartBookVo.setBookImage(Book.getImage());
                    CartBookVo.setBookName(Book.getName());
                    CartBookVo.setBookStatus(Book.getStatus());
                    CartBookVo.setBookPrice(Book.getPrice());
                    CartBookVo.setBookStock(Book.getStock());
                    //判断库存
                    int buyLimitCount = 0;
                    if(Book.getStock() >= cartItem.getQuantity()){
                        //库存充足的时候
                        buyLimitCount = cartItem.getQuantity();
                        CartBookVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    }else{
                        buyLimitCount = Book.getStock();
                        CartBookVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                        //购物车中更新有效库存
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setId(cartItem.getId());
                        cartForQuantity.setQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                    }
                    CartBookVo.setQuantity(buyLimitCount);
                    //计算总价
                    CartBookVo.setBookTotalPrice(BigDecimalUtil.mul(Book.getPrice().doubleValue(),CartBookVo.getQuantity()));
                    CartBookVo.setBookChecked(cartItem.getChecked());
                }

                if(cartItem.getChecked() == Const.Cart.CHECKED){
                    //如果已经勾选,增加到整个的购物车总价中
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),CartBookVo.getBookTotalPrice().doubleValue());
                }
                CartBookVoList.add(CartBookVo);
            }
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartBookVoList(CartBookVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        return cartVo;
    }
    //判读是否已经都勾选
    private boolean getAllCheckedStatus(Integer userId){
        if(userId == null){
            return false;
        }
        return cartMapper.selectCartBookCheckedStatusByUserId(userId) == 0;

    }


























}
