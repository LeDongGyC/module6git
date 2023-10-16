import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  forgotPasswordForm: FormGroup;
  validationMessage = {
    username: [
      {type: 'required', message: 'Vui lòng không để trống!'},
      {type: 'maxlength', message: 'Tên tài khoản không được quá 20 ký tự'}
    ]
  };
  constructor(private router: Router,
              private formBuilder: FormBuilder,
              private authhService: AuthService,
              private toastrService: ToastrService,
              private titleService: Title) {
    this.titleService.setTitle('Quên mật khẩu');
  }

  ngOnInit(): void {
    this.forgotPasswordForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.maxLength(20)]]
    });
  }
  submit() {
    if ((this.forgotPasswordForm.value.username + '').trim() === '') {
      this.toastrService.error('Tên tài khoản không được để trống', 'Gửi email thất bại:', {
        timeOut: 5000,
        extendedTimeOut: 1500
      });
    } else {
      this.authhService.forgotPassword(this.forgotPasswordForm.value).subscribe(
        data => {
          this.toastrService.success('Vui lòng kiểm tra Email của bạn để xác thực!', 'Gửi Email thành công:', {
            timeOut: 5000,
            extendedTimeOut: 1500
          });
          this.router.navigateByUrl('/verify-change-password/');
        },
        err => {
          this.forgotPasswordForm.reset();
          this.toastrService.error('Tên tài khoản chưa chính xác hoặc chưa được đăng ký', 'Gửi email thất bại:', {
            timeOut: 5000,
            extendedTimeOut: 1500
          });
        }
      );
    }
  }
}
